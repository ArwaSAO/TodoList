package com.kotlin.todolist.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// starting creating table
@Entity
data class ToDoListItemModel(
    val taskName: String,
    val taskDescription: String,
    val taskDate: String,
    val taskDodate: String,
    var status: Boolean,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)