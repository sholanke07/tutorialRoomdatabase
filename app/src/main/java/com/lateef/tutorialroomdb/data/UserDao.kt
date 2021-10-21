package com.lateef.tutorialroomdb.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lateef.tutorialroomdb.model.User

@Dao
interface UserDao {

    //the onConflict= OnConflictStrategy means if their is a new exsactlly the same user it should just ignore it
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun adduser(user: User)

    //this used to select all items in the query
    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Update
    suspend fun updateUser(user: User)

    // this is used to delete one user
    @Delete
    suspend fun deleteUser(user: User)

    //this is used to delete all users
    @Query("DELETE FROM user_table")
    suspend fun deleteAllUser()
}