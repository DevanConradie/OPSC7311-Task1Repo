package com.example.opsc7311_task1

// ChecklistActivity.kt

import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged

class ChecklistActivity : AppCompatActivity() {

    private lateinit var checkBox1: CheckBox
    private lateinit var editTextTask1: EditText

    private lateinit var checkBox2: CheckBox
    private lateinit var editTextTask2: EditText

    private lateinit var checkBox3: CheckBox
    private lateinit var editTextTask3: EditText

    private lateinit var checkBox4: CheckBox
    private lateinit var editTextTask4: EditText

    private lateinit var checkBox5: CheckBox
    private lateinit var editTextTask5: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist)

        // Initialize views
        checkBox1 = findViewById(R.id.checkBox1)
        editTextTask1 = findViewById(R.id.editTextTask1)

        checkBox2 = findViewById(R.id.checkBox2)
        editTextTask2 = findViewById(R.id.editTextTask2)

        checkBox3 = findViewById(R.id.checkBox3)
        editTextTask3 = findViewById(R.id.editTextTask3)

        checkBox4 = findViewById(R.id.checkBox4)
        editTextTask4 = findViewById(R.id.editTextTask4)

        checkBox5 = findViewById(R.id.checkBox5)
        editTextTask5 = findViewById(R.id.editTextTask5)

        // Set up listeners
        setupEditTextListeners()
    }

    private fun setupEditTextListeners() {
        editTextTask1.doAfterTextChanged {
            // Handle changes to Task 1
        }

        editTextTask2.doAfterTextChanged {
            // Handle changes to Task 2
        }

        editTextTask3.doAfterTextChanged {
            // Handle changes to Task 3
        }

        editTextTask4.doAfterTextChanged {
            // Handle changes to Task 4
        }

        editTextTask5.doAfterTextChanged {
            // Handle changes to Task 5
        }
    }
}
