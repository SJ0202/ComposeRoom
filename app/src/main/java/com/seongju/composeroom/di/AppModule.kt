package com.seongju.composeroom.di

import android.app.Application
import androidx.room.Room
import com.seongju.composeroom.data.database.UserDatabase
import com.seongju.composeroom.data.repository.UserRepositoryImpl
import com.seongju.composeroom.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserData(application: Application): UserDatabase {
        return Room.databaseBuilder(
            application,
            UserDatabase::class.java,
            "LocalUserDatabase"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserRepository(database: UserDatabase): UserRepository {
        return UserRepositoryImpl(userDao = database.userDao)
    }

}