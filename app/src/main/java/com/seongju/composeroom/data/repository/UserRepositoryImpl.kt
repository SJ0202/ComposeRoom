package com.seongju.composeroom.data.repository

import com.seongju.composeroom.data.database.UserDao
import com.seongju.composeroom.domain.model.UserModel
import com.seongju.composeroom.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userDao: UserDao
): UserRepository {

    override suspend fun upsertUser(userModel: UserModel) {
        userDao.upsertUser(userModel = userModel)
    }

    override suspend fun getUser(userName: String, password: String): List<UserModel> {
        return userDao.getUser(userName = userName, password = password)
    }

}