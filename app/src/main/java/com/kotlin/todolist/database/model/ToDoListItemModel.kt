package com.kotlin.todolist.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// starting creating table
@Entity
data class ToDoListItemModel(
    var taskName: String,
    var taskDescription: String,
    var taskDate: String,
    var taskDuedate: String,
    var status: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)