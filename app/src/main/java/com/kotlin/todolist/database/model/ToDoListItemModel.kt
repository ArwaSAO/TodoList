package com.kotlin.todolist.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// starting creating table
@Entity
data class ToDoListItemModel(
    var taskName: String,
    var taskDescription: String,
    var taskDate: String,
    var taskDodate: String,
    var status: Boolean,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)