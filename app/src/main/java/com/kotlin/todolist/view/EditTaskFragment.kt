package com.kotlin.todolist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kotlin.todolist.R

class EditTaskFragment : Fragment() {
    private val toDoListViewModel: ToDoListModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_edit_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val addTaskEditText: EditText = view.findViewById(R.id.add_your_task)
        val addTasDescriptionEditText: EditText = view.findViewById(R.id.your_task_description)
        val addTaskDate: EditText = view.findViewById(R.id.your_task_date)
        val datePicker: DatePicker = view.findViewById(R.id.date_Picker)
        val saveButton: Button = view.findViewById(R.id.task_save_button)

        saveButton.setOnClickListener {
            val task = addTaskEditText.text.toString()
            val taskDescription = addTasDescriptionEditText.text.toString()
            val taskDate = addTaskDate.text.toString()
            val calender = datePicker.maxDate.toString()
            if (task.isNotEmpty() && taskDescription.isNotEmpty()) {

                toDoListViewModel.addItem(task, taskDescription, taskDate, calender, false)

                findNavController().popBackStack()

            }

        }
    }


}