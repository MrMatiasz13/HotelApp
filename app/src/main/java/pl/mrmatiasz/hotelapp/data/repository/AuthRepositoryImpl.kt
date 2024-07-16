package pl.mrmatiasz.hotelapp.data.repository

import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import pl.mrmatiasz.hotelapp.domain.repository.AuthRepository
import pl.mrmatiasz.hotelapp.util.Resource
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val goTrue: GoTrue
): AuthRepository {
    override suspend fun login(email: String, password: String): Flow<Resource<Unit>> {
        return flow {
            emit(Resource.Loading())
            val result = goTrue.loginWith(Email) {
                this.email = email
                this.password = password
            }
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }

    override suspend fun register(email: String, password: String): Flow<Resource<Email.Result>> {
        return flow {
            emit(Resource.Loading())
            val result = goTrue.signUpWith(Email) {
                this.email = email
                this.password = password
            }
            emit(Resource.Success(result!!))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }
}