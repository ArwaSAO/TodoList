package com.kotlin.todolist.view

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.kotlin.todolist.R
import com.kotlin.todolist.database.model.ToDoListItemModel
import java.util.*


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
                yourTaskDoDateTextView.setText(item.taskDodate)
                yourTaskDescriptionTextView.setText(item.taskDescription)
                selectedItem = item

            }
        })

        editTaskButton.setOnClickListener {
            selectedItem.taskName = yourTaskTextView.text.toString()
            selectedItem.taskDodate = yourTaskDoDateTextView.text.toString()
            selectedItem.taskDescription = yourTaskDescriptionTextView.text.toString()

            listViewModel.updateItem(selectedItem)
            findNavController().popBackStack()
        }

        editdate.setOnClickListener {

            //getting current day,month and year.

            val calendar: Calendar = Calendar.getInstance()
            val year: Int = calendar.get(Calendar.YEAR)
            val month: Int = calendar.get(Calendar.MONTH)
            val day: Int = calendar.get(Calendar.DAY_OF_MONTH)


            val dpd = DatePickerDialog(view.context, DatePickerDialog.OnDateSetListener { view, year, month, day ->
                yourTaskDoDateTextView.setText("" + day + "/" + month + "/" + year)
            }, year, month, day)
            dpd.show()
        }
//        taskBackButton.setOnClickListener {
//            listViewModel.deleteItem(selectedItem)
//
//            findNavController().popBackStack()
//        }

    }


}