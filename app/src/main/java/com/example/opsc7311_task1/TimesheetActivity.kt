package com.example.opsc7311_task1

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.opsc7311_task1.databinding.ActivityTimesheetBinding  // Import the View Binding class

class TimesheetActivity : AppCompatActivity() {

    private lateinit var categoryManager: CategoryManager
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var binding: ActivityTimesheetBinding  // Declare a binding variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimesheetBinding.inflate(layoutInflater)  // Initialize the binding
        val view = binding.root
        setContentView(view)

        // Initialize CategoryManager and RecyclerView
        categoryManager = CategoryManager(this)
        categoryAdapter = CategoryAdapter(categoryManager.getCategories())

        // Set up RecyclerView for categories
        val recyclerView: RecyclerView = binding.categoryRecyclerView  // Use View Binding
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = categoryAdapter

        // Initialize UI elements
        val addCategoryButton: Button = binding.addCategoryButton  // Use View Binding

        // Set click listener for adding a category
        addCategoryButton.setOnClickListener {
            val categoryName = binding.categoryNameEditText.text.toString()  // Use View Binding
            val newCategory = Category(System.currentTimeMillis(), categoryName)  // Check Category class
            categoryManager.addCategory(newCategory)
            categoryAdapter.updateCategories(categoryManager.getCategories())  // Check CategoryAdapter methods
        }
    }
}
