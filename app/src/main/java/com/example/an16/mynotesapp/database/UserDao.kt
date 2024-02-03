package com.example.an16.mynotesapp.database

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface UserDao {

    @Insert
    suspend fun addUser(userEntity: UserEntity): Long
}