package com.seongju.composeroom.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.seongju.composeroom.domain.model.UserModel

@Database(
    entities = [UserModel::class],
    version = 1
)
@TypeConverters(LocalDateConverter::class)
abstract class UserDatabase: RoomDatabase() {

    abstract val userDao: UserDao

}