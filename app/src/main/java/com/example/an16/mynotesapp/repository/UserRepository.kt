package com.example.an16.mynotesapp.repository

import com.example.an16.mynotesapp.database.UserDao
import com.example.an16.mynotesapp.database.UserEntity
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {

    suspend fun addUser(email: String):Long {
        return userDao.addUser(UserEntity(0, email))
    }
}