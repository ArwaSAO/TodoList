package com.kotlin.todolist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.todolist.R
import com.kotlin.todolist.database.model.ToDoListItemModel

class ToDoListAdapter(val items:List<ToDoListItemModel>, val toDoListItemViewModel: ToDoListModel):
RecyclerView.Adapter<ToDoListAdapter.ToDoListViewHolder>(){

    class ToDoListViewHolder(view:View):RecyclerView.ViewHolder(view){

        val taskNameTextView:TextView = view.findViewById(R.id.add_your_task)
        val taskDescriptionTextView:TextView = view.findViewById(R.id.your_task_description)
        val taskDateTextView: TextView = view.findViewById(R.id.your_task_date)
        val taskDoDateTextView:TextView = view.findViewById(R.id.yourtask_dodate)
        val taskStatusCheckBox:CheckBox = view.findViewById(R.id.task_checkbox)
        val backButton: Button = view.findViewById(R.id.back_button)
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
        holder.taskDescriptionTextView.text =  item.taskDescription
        holder.taskDateTextView.text = item.taskDate.toString()
        holder.taskDoDateTextView.text = item.taskDodate.toString()


        holder.backButton.setOnClickListener {view ->
            toDoListItemViewModel.selectedItemMutableLiveData.postValue(item)
            view.findNavController().navigate(R.id.action_checkListFragment1_to_taskDetailsFragment)
        }

        holder.taskStatusCheckBox.setOnClickListener {
            item.status = holder.taskStatusCheckBox.isChecked
            toDoListItemViewModel.updateItem(item)
        }


    }

    override fun getItemCount(): Int {
        return items.size
    }
}
