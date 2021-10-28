package com.kotlin.todolist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kotlin.todolist.R
import com.kotlin.todolist.database.model.ToDoListItemModel


class TaskDetailsFragment : Fragment() {


    private val inventoryViewModel: ToDoListModel by activityViewModels()
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

        val yourTaskTextView: TextView = view.findViewById(R.id.yourtask_textview)
        val yourTaskDescriptionTextView: TextView = view.findViewById(R.id.yourtask_description)
        val yourTaskDateTextView: TextView = view.findViewById(R.id.yourtask_date)
        val yourTaskDoDateTextView: TextView = view.findViewById(R.id.yourtask_dodate)
        val taskBackButton: Button =view.findViewById(R.id.back_button)

        inventoryViewModel.selectedItemMutableLiveData.observe(viewLifecycleOwner, Observer {
            it?.let { item ->
                yourTaskTextView.text = item.task
                yourTaskDescriptionTextView.text = item.taskDescription
                yourTaskDateTextView.text = item.taskdate
                yourTaskDoDateTextView.text = item.taskDodate
                selectedItem = item

            }
        })

        taskBackButton.setOnClickListener {
            inventoryViewModel.deleteItem(selectedItem)
            findNavController().popBackStack()
        }

    }



}