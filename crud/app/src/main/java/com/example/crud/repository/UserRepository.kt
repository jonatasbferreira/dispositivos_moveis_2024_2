package com.example.crud.repository

import com.example.crud.dao.UserDao
import com.example.crud.model.User
import kotlinx.coroutines.flow.Flow

class UserRepository (private val userDao: UserDao){
    suspend fun insertUser(user: User) = userDao.insertUser(user)

    fun getAllUsers(): Flow<List<User>> = userDao.getAllUsers()

    suspend fun updateUser(user: User) = userDao.updateUser(user)

    suspend fun deleteUser(user: User) = userDao.deleteUser(user)

}