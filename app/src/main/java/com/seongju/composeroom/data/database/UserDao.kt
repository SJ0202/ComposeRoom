package com.seongju.composeroom.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.seongju.composeroom.domain.model.UserModel

@Dao
interface UserDao {

    @Upsert
    suspend fun upsertUser(userModel: UserModel)

    @Query("SELECT * FROM user WHERE userName = :userName AND password = :password")
    suspend fun getUser(userName: String, password: String): List<UserModel>

}