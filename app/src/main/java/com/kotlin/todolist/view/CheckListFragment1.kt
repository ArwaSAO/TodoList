package com.kotlin.todolist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kotlin.todolist.R
import com.kotlin.todolist.database.model.ToDoListItemModel


class CheckListFragment1 : Fragment() {

    private val toDoListItems = mutableListOf<ToDoListItemModel>()
    private val toDoListViewModel: ToDoListModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_check_list1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listRecyclerView: RecyclerView = view.findViewById(R.id.check_list_recycler_view)
        val addFloatingButton: FloatingActionButton = view.findViewById(R.id.floatingActionButton)
      //  val myCheckList: TextView = view.findViewById<TextView>(R.id.check_list_text_view)

        val listAdapter = ToDoListAdapter(toDoListItems, toDoListViewModel)
        listRecyclerView.adapter = listAdapter

        // observer this line is working for live data

        toDoListViewModel.toDoListItems.observe(viewLifecycleOwner, Observer {
            it?.let { items ->
                toDoListItems.clear()
                toDoListItems.addAll(items)
                listAdapter.notifyDataSetChanged()
            }
        })
        // add action to the floating button
        addFloatingButton.setOnClickListener {
            findNavController().navigate(R.id.action_checkListFragment1_to_editTaskFragment)
        }
    }




}