package com.seongju.composeroom.di

import com.seongju.composeroom.domain.repository.UserRepository
import com.seongju.composeroom.domain.user_case.get.GetUsersUseCase
import com.seongju.composeroom.domain.user_case.upsert.UpsertUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideUpsertUserUseCase(userRepository: UserRepository): UpsertUserUseCase {
        return UpsertUserUseCase(userRepository = userRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetUsersUseCase(userRepository: UserRepository): GetUsersUseCase {
        return GetUsersUseCase(userRepository = userRepository)
    }

}