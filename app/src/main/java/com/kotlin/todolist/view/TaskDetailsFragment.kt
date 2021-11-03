package com.kotlin.todolist.view

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.kotlin.todolist.R
import com.kotlin.todolist.database.model.ToDoListItemModel


class TaskDetailsFragment : Fragment() {


    private val listViewModel: ToDoListModel by activityViewModels()
    private lateinit var selectedItem: ToDoListItemModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_details, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val yourTaskTextView: TextView = view.findViewById(R.id.yourtask_edittext)
        val yourTaskDoDateTextView: TextView = view.findViewById(R.id.task_dodate_textview)
        val yourTaskDescriptionTextView: TextView = view.findViewById(R.id.taskdescription_edittext)
        //val yourTaskDateTextView: TextView = view.findViewById(R.id.yourtask_date)
      //  val taskBackButton: Button = view.findViewById(R.id.back_button)
        val editTaskButton: ImageButton = view.findViewById(R.id.edittask_button)
        val editdate: ImageButton = view.findViewById(R.id.editdate_imageButton)

        listViewModel.selectedItemMutableLiveData.observe(viewLifecycleOwner, Observer {
            it?.let { item ->
                yourTaskTextView.setText(item.taskName)
                yourTaskDoDateTextView.setText(item.taskDuedate)
                yourTaskDescriptionTextView.setText(item.taskDescription)
                selectedItem = item

            }
        })

        editTaskButton.setOnClickListener {
            selectedItem.taskName = yourTaskTextView.text.toString()
            selectedItem.taskDuedate = yourTaskDoDateTextView.text.toString()
            selectedItem.taskDescription = yourTaskDescriptionTextView.text.toString()

            listViewModel.updateItem(selectedItem)
            findNavController().popBackStack()
        }

        val dateRangePicker = DatePickerDialog(requireActivity())
        yourTaskDoDateTextView.setOnClickListener {

            dateRangePicker.show()
//            val selected = dateRangePicker.selection
//            duedateTextView.setText(selected.toString())
        }

        dateRangePicker.setButton(DialogInterface.BUTTON_POSITIVE, "OK") { _, _ ->
            yourTaskDoDateTextView.setText("${dateRangePicker.datePicker.year}/" +
                    "${dateRangePicker.datePicker.month}/" +
                    "${dateRangePicker.datePicker.dayOfMonth}")
        }
//        taskBackButton.setOnClickListener {
//            listViewModel.deleteItem(selectedItem)
//
//            findNavController().popBackStack()
//        }

    }


}