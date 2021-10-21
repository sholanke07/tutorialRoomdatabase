package com.lateef.tutorialroomdb.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lateef.tutorialroomdb.model.User

@Database(entities= [User::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object{
        @Volatile  // means things in this field are made visible to other threads
        private var INSTANCE: UserDatabase?= null

        //in there we are checking if the instance is not 0
        fun getDatabase(context: Application): UserDatabase{
            val tempInstance = INSTANCE

            //if no instance we are creating one
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,     UserDatabase::class.java, "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }
}