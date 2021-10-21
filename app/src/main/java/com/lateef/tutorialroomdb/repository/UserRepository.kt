package com.lateef.tutorialroomdb.repository

import androidx.lifecycle.LiveData
import com.lateef.tutorialroomdb.data.UserDao
import com.lateef.tutorialroomdb.model.User

class UserRepository(private val userDao: UserDao) {

    //to read all user
    val readAllData: LiveData<List<User>> = userDao.readAllData()

    // to add user
    suspend fun addUser(user: User){
        userDao.adduser(user)
    }

    // to update user
    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

    // to delete one user
    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

    //to delete All users
    suspend fun deleteAllUser(){
        userDao.deleteAllUser()
    }
}