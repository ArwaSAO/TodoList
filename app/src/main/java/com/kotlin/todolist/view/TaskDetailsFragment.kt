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

        val nameTextView: TextView = view.findViewById(R.id.name_item_textView)
        val priceTextView: TextView = view.findViewById(R.id.item_price_textView2)
        val inStockTextView: TextView = view.findViewById(R.id.item_instock_textView4)
        val deleteButton: Button = view.findViewById(R.id.delete_button)

        inventoryViewModel.selectedItemMutableLiveData.observe(viewLifecycleOwner, Observer {
            it?.let { item ->
                nameTextView.text = item.name
                priceTextView.text = "${item.price} SAR "
                inStockTextView.text = "In Stock:${item.inStock}"
                selectedItem = item

            }
        })

        deleteButton.setOnClickListener {
            inventoryViewModel.deleteItem(selectedItem)
            findNavController().popBackStack()
        }

    }



}