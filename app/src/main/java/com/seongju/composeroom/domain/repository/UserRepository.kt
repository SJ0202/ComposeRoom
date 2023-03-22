package com.seongju.composeroom.domain.repository

import com.seongju.composeroom.domain.model.UserModel

interface UserRepository {

    suspend fun upsertUser(userModel: UserModel)

    suspend fun getUser(userName: String, password: String): List<UserModel>

}