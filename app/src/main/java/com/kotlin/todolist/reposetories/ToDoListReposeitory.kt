package com.kotlin.todolist.reposetories

import android.content.Context
import androidx.room.Room
import com.kotlin.todolist.database.TaskDao
import com.kotlin.todolist.database.ToDoListDataBase
import com.kotlin.todolist.database.model.ToDoListItemModel
import java.lang.Exception

private const val DATABASE_NAME = "todolist_database"

class ToDoListRepository(context: Context) {
    private val database: ToDoListDataBase =
        Room.databaseBuilder(
            context,
            ToDoListDataBase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()

    private val taskDao = database.TaskDao()

    fun getItems() = taskDao.getItems()

    suspend fun addItem(itemModel: ToDoListItemModel) = taskDao.addItemModel(itemModel)
    suspend fun upDateItem(itemModel: ToDoListItemModel) = taskDao.UpdateItemModel(itemModel)
    suspend fun deleteItem(itemModel: ToDoListItemModel) = taskDao.deleteItemModel(itemModel)

    companion object {
        private var instance: ToDoListRepository? = null

        // assign for declaration
        fun init(context: Context) {
            if (instance == null)
                instance = ToDoListRepository(context)


        }

        fun get(): ToDoListRepository {
            return instance ?: throw Exception("ToDoList Repository must be  initialize")
        }

    }

}