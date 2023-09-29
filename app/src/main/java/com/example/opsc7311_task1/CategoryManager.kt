package com.example.opsc7311_task1

import android.content.Context
import com.example.opsc7311_task1.Category
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class CategoryManager(context: Context) {

    // You can use SharedPreferences, a local database, or any other storage mechanism
    private val sharedPreferences = context.getSharedPreferences("categories", Context.MODE_PRIVATE)

    fun addCategory(category: Category) {
        // Add the category to your storage mechanism
        // Example using SharedPreferences:
        val categories = getCategories().toMutableList()
        categories.add(category)
        sharedPreferences.edit()
            .putString("categories", Gson().toJson(categories)) // Use Gson to serialize/deserialize
            .apply()
    }

    fun getCategories(): List<Category> {
        // Retrieve categories from your storage mechanism
        // Example using SharedPreferences:
        val categoriesJson = sharedPreferences.getString("categories", null)
        return if (categoriesJson != null) {
            Gson().fromJson(categoriesJson, object : TypeToken<List<Category>>() {}.type)
        } else {
            emptyList()
        }
    }
}
