package com.lateef.tutorialroomdb.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.lateef.tutorialroomdb.data.UserDatabase
import com.lateef.tutorialroomdb.repository.UserRepository
import com.lateef.tutorialroomdb.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

     val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    //this add user was used in addfragment to add to database
    fun addUser(user: User){
        //this mean i want run this code in background thread
        viewModelScope.launch(Dispatchers.IO){
            repository.addUser(user)

        }
    }

    //used to update user
    fun updateUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateUser(user)
        }
    }

    //used to delete one user
    fun deleteUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteUser(user)
        }
    }

    //used to delete all users
    fun deleteAllUser(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllUser()
        }
    }
}