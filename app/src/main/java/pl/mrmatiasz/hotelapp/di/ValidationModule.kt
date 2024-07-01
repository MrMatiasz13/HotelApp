package pl.mrmatiasz.hotelapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.mrmatiasz.hotelapp.domain.use_case.validation.ValidateEmailUseCase
import pl.mrmatiasz.hotelapp.domain.use_case.validation.ValidatePasswordUseCase
import pl.mrmatiasz.hotelapp.domain.use_case.validation.ValidateRepeatPasswordUseCase
import pl.mrmatiasz.hotelapp.domain.use_case.validation.ValidateUsernameUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ValidationModule {

    @Provides
    @Singleton
    fun providesUsernameValid() = ValidateUsernameUseCase()

    @Provides
    @Singleton
    fun providesEmailValid() = ValidateEmailUseCase()

    @Provides
    @Singleton
    fun providesPasswordValid() = ValidatePasswordUseCase()

    @Provides
    @Singleton
    fun providesRepeatedPasswordValid() = ValidateRepeatPasswordUseCase()
}