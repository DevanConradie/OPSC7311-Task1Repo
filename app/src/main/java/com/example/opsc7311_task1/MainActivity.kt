package com.example.opsc7311_task1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.opsc7311_task1.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Initialize Firebase
        FirebaseApp.initializeApp(this)
        database = FirebaseDatabase.getInstance()
        myRef = database.getReference("message")

        // Set up click listener for the button
        binding.loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

            // Hardcode "Hello, World! : 12" and write to the database
            myRef.setValue("Hello, World! : 12")
                .addOnSuccessListener {
                    Log.d("MainActivity", "Data written to the default database path")
                }
                .addOnFailureListener {
                    Log.e("MainActivity", "Error writing to the default database path", it)
                }

            // Additional change: Write "Hello, World!" to a specific path
            val customPathRef = database.getReference("customPath/Message")
            customPathRef.setValue("Hello, World!")
                .addOnSuccessListener {
                    Log.d("MainActivity", "Data written to the custom path")
                }
                .addOnFailureListener {
                    Log.e("MainActivity", "Error writing to the custom path", it)
                }
        }
    }
}
