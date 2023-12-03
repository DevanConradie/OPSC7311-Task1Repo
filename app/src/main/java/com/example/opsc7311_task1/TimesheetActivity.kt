// TimesheetActivity.kt in com.example.opsc7311_task1 package
package com.example.opsc7311_task1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.opsc7311_task1.databinding.ActivityTimesheetBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class TimesheetActivity : AppCompatActivity() {

    private lateinit var categoryManager: CategoryManager
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var binding: ActivityTimesheetBinding

    // Get a reference to the Realtime Database
    private val databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("Category")

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

            // Log the category name (for debugging)
            Log.d("TimesheetActivity", "CategoryName: $categoryName")

            // Check if the category name is not empty before updating the UI
            if (categoryName.isNotEmpty()) {
                // Create a reference to a new child node with a unique key
                val categoryReference = databaseReference.push()

                // Write the category name to the generated key
                categoryReference.setValue(categoryName)

                // Log statement for debugging
                Log.d("TimesheetActivity", "Category added to Firebase")

                // Update the local categories and refresh the UI
                categoryManager.addCategory(
                    Category(
                        System.currentTimeMillis(),
                        categoryReference.key!!, // Use the unique key as the category ID
                        categoryName
                    )
                )
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

        // Initialize the "To Do List" button
        val toDoListButton: Button = binding.toDoListButton

        // Set click listener for the "To Do List" button
        toDoListButton.setOnClickListener {
            // Create an intent to navigate to ChecklistActivity
            val intent = Intent(this, ChecklistActivity::class.java)
            startActivity(intent)
        }
    }
}
