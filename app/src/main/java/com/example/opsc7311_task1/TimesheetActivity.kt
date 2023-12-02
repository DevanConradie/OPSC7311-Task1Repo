package com.example.opsc7311_task1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.opsc7311_task1.Category
import com.example.opsc7311_task1.CategoryAdapter
import com.example.opsc7311_task1.CategoryDetailActivity
import com.example.opsc7311_task1.CategoryManager
import com.example.opsc7311_task1.databinding.ActivityTimesheetBinding
import com.google.firebase.Firebase
import com.google.firebase.database.database

class TimesheetActivity : AppCompatActivity() {

    private lateinit var categoryManager: CategoryManager
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var binding: ActivityTimesheetBinding

    val database = Firebase.database
    val myRef = database.getReference("message")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimesheetBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Initialize CategoryManager and RecyclerView
        categoryManager = CategoryManager(this)
        categoryAdapter = CategoryAdapter(categoryManager.getCategories())

        // Set up RecyclerView for categories
        val recyclerView: RecyclerView = binding.categoryRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = categoryAdapter

        // Initialize UI elements
        val addCategoryButton: Button = binding.addCategoryButton

        // Set click listener for adding a category
        addCategoryButton.setOnClickListener {
            val categoryName = binding.categoryNameEditText.text.toString()

            // Check if the category name is not empty before updating the UI
            if (categoryName.isNotEmpty()) {
                // Write the category name to Realtime Database
                myRef.setValue(categoryName)

                // Update the local categories and refresh the UI
                categoryManager.addCategory(Category(System.currentTimeMillis(), categoryName))
                categoryAdapter.updateCategories(categoryManager.getCategories())

                // Clear the EditText after adding the category
                binding.categoryNameEditText.text.clear()
            }
        }

        // Initialize the "View Entries" button
        val viewEntriesButton: Button = binding.viewEntriesButton

        // Set click listener for the "View Entries" button
        viewEntriesButton.setOnClickListener {
            // Create an intent to navigate to CategoryDetailActivity
            val intent = Intent(this, CategoryDetailActivity::class.java)
            // You can pass data to CategoryDetailActivity here if needed
            startActivity(intent)
        }
    }
}
