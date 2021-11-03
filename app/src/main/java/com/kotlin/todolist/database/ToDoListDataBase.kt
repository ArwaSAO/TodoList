package com.kotlin.todolist.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kotlin.todolist.database.model.ToDoListItemModel



@Database(entities = [ToDoListItemModel::class], version = 3)
abstract class ToDoListDataBase: RoomDatabase() {

    abstract fun TaskDao():TaskDao
    //abstract fun TaskDao(): Any
}