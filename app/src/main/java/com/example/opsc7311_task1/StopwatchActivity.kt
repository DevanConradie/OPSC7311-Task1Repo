package com.example.opsc7311_task1

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class StopwatchActivity : AppCompatActivity() {

    private lateinit var daysSpinner: Spinner
    private lateinit var minutesSpinner: Spinner
    private lateinit var setReminderButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stopwatch)

        daysSpinner = findViewById(R.id.daysSpinner)
        minutesSpinner = findViewById(R.id.minutesSpinner)
        setReminderButton = findViewById(R.id.setReminderButton)

        // Set up Days of the Week Spinner
        val daysAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.days_of_week,
            android.R.layout.simple_spinner_item
        )
        daysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        daysSpinner.adapter = daysAdapter

        // Set up Minutes Spinner
        val minutesAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.minute_intervals,
            android.R.layout.simple_spinner_item
        )
        minutesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        minutesSpinner.adapter = minutesAdapter

        // Set up click listener for the "Set Reminder" button
        setReminderButton.setOnClickListener {
            showToast("Reminder Set!")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

