package com.kotlin.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kotlin.todolist.database.model.ToDoListItemModel


@Dao
interface TaskDao {




    @Insert
    suspend fun addItemModel(toDoListItemModel: ToDoListItemModel)

    @Query("SELECT * FROM todolistitemmodel")
    fun getItems():LiveData<List<ToDoListItemModel>>


    @Update
    suspend fun UpdateItemModel(toDoListItemModel: ToDoListItemModel)


    @Delete
    suspend fun deleteItemModel(itemModel: ToDoListItemModel)


}