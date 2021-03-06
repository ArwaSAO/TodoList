package com.kotlin.todolist.view

import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.todolist.R
import com.kotlin.todolist.database.model.ToDoListItemModel
import java.text.SimpleDateFormat
import java.util.*

class ToDoListAdapter(val items:List<ToDoListItemModel>, val toDoListItemViewModel: ToDoListModel):
RecyclerView.Adapter<ToDoListAdapter.ToDoListViewHolder>(){

    class ToDoListViewHolder(view:View):RecyclerView.ViewHolder(view){

        val taskNameTextView:TextView = view.findViewById(R.id.task_text_view)
        val taskdodate:TextView = view.findViewById(R.id.task_dodate_text_view)
        val taskStatusCheckBox:CheckBox = view.findViewById(R.id.task_checkBox)
        val deleteTaskButton: ImageButton = view.findViewById(R.id.delete_image_button)
       // val taskdate: TextView = view.findViewById(R.id.)
        //val editTaskButton: ImageButton = view.findViewById(R.id.edit_button)


    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): ToDoListViewHolder {
        return ToDoListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.ltem_layout,
                parent,
                false
            )
        )


    }

    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {
        val item = items[position]

        holder.taskNameTextView.text = item.taskName
        holder.taskdodate.text =  item.taskDuedate
       // holder.taskdate.text =  item.taskDate

        holder.deleteTaskButton.setOnClickListener {view ->
            toDoListItemViewModel.deleteItem(item)

        }
       // holder.editTaskButton.setOnClickListener {view ->
        //    toDoListItemViewModel.selectedItemMutableLiveData.postValue(item)
       // }

        holder.itemView.setOnClickListener {
         toDoListItemViewModel.selectedItemMutableLiveData.postValue(item)
            it.findNavController().navigate(R.id.action_checkListFragment1_to_taskDetailsFragment)
        }


        val currentDate = Date()
        val format = SimpleDateFormat("yyyy/MM/dd")
        val deadline = format.parse(item.taskDuedate)
        if (currentDate>deadline){
            holder.taskNameTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }

        holder.taskStatusCheckBox.setOnClickListener {
            item.status = holder.taskStatusCheckBox.isChecked
            toDoListItemViewModel.updateItem(item)

            if(holder.taskStatusCheckBox.isChecked){
            holder.taskNameTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG)
                holder.taskNameTextView.setTextColor(Color.GRAY)
        }else {
            holder.taskNameTextView.setPaintFlags(0)
                holder.taskNameTextView.setTextColor(Color.BLACK)
            }
        }



    }

    override fun getItemCount(): Int {
        return items.size
    }
}

