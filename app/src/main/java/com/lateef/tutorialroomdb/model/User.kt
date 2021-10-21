package com.lateef.tutorialroomdb.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val fristname: String,
    val lastname: String,
    val age: Int): Parcelable

// Parcelable is for us to be able to pass our user data
