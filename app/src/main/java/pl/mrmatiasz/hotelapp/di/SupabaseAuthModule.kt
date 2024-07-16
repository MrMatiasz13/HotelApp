package pl.mrmatiasz.hotelapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.annotiations.SupabaseExperimental
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.FlowType
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.gotrue
import pl.mrmatiasz.hotelapp.BuildConfig
import pl.mrmatiasz.hotelapp.data.repository.AuthRepositoryImpl
import pl.mrmatiasz.hotelapp.domain.repository.AuthRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SupabaseAuthModule {

    @OptIn(SupabaseExperimental::class)
    @Provides
    @Singleton
    fun supabaseClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = BuildConfig.SUPABASE_URL,
            supabaseKey = BuildConfig.SUPABASE_API_KEY
        ) {
            install(GoTrue) {
                flowType = FlowType.PKCE
                scheme = "app"
                host = "supabase.com"
            }
        }
    }

    @Provides
    @Singleton
    fun supabaseGoTrue(client: SupabaseClient): GoTrue {
        return client.gotrue
    }

    @Provides
    @Singleton
    fun authRepositoryImpl(): AuthRepository {
        return AuthRepositoryImpl(
            supabaseGoTrue(supabaseClient())
        )
    }
}