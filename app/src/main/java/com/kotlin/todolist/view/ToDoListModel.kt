package com.kotlin.todolist.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.todolist.database.model.ToDoListItemModel
import com.kotlin.todolist.reposetories.ToDoListRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class ToDoListModel:ViewModel() {

    private val toDoListRepository = ToDoListRepository.get()

    var toDoListItems = toDoListRepository .getItems()
    var selectedItemMutableLiveData = MutableLiveData<ToDoListItemModel>()

    fun addItem(taskName:String, taskDescription:String, taskDate: String, taskDodate: String, status:Boolean){


        val simpleDateFormat = SimpleDateFormat("dd/M/yyy hh:mm:ss")
        val currentDate = simpleDateFormat.format(Date())
        viewModelScope.launch {
            toDoListRepository.addItem(ToDoListItemModel(taskName, taskDescription,currentDate,taskDodate, status))
        }
    }


    fun updateItem(itemModel: ToDoListItemModel) {
        viewModelScope.launch {
            toDoListRepository.upDateItem(itemModel)
        }
    }

    fun deleteItem(itemModel: ToDoListItemModel) {
        viewModelScope.launch {
            toDoListRepository.deleteItem(itemModel)
        }
    }



}