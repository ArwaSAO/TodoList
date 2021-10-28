package com.kotlin.todolist.database.model

import android.app.ActivityManager
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

// starting creating table
@Entity
data class ToDoListItemModel(
    val taskName: String,
    val taskDescription: String,
    val taskDate: Date,
    val taskDodate: Date,
    val status: Boolean,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)