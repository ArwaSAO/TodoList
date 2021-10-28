package com.kotlin.todolist.view

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.todolist.database.model.ToDoListItemModel

class ToDoListAdapter(val items:List<ToDoListItemModel>,val toDoListItemModel: ToDoListItemModel):
RecyclerView.Adapter<ToDoListAdapter.ToDoListViewHolder>(){

    val taskName: TextView = view.findViewById
}