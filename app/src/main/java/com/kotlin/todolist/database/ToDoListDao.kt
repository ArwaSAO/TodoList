package com.kotlin.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kotlin.todolist.database.model.ToDoListItemModel


@Dao
interface ToDoListDao {




    @Insert
    suspend fun addItem(toDoListItemModel: ToDoListItemModel)

    @Query("SELECT * FROM todolistitemmodel")
    fun getItems():LiveData<List<ToDoListItemModel>>
}