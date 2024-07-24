package pl.mrmatiasz.hotelapp.domain.repository

import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.flow.Flow
import pl.mrmatiasz.hotelapp.util.Resource

interface AuthRepository {
    suspend fun login(email: String, password: String): Flow<Resource<Unit>>
    suspend fun register(email: String, password: String): Flow<Resource<Email.Result>>

    suspend fun logOut(): Flow<Resource<Unit>>
}