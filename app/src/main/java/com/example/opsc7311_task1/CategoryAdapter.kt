package com.example.opsc7311_task1

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.opsc7311_task1.Category
import com.example.opsc7311_task1.CategoryDetailActivity


class  CategoryAdapter(private var categories: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.categoryNameTextView)
    }

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        context = parent.context
        val itemView = LayoutInflater.from(context)
            .inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.nameTextView.text = category.name

        // Set a click listener for the item
        holder.itemView.setOnClickListener {
            // Create an intent to launch CategoryDetailActivity
            val intent = Intent(context, CategoryDetailActivity::class.java)
            // Pass any data you want to CategoryDetailActivity here
            intent.putExtra("category_name", category.name)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    fun updateCategories(newCategories: List<Category>) {
        categories = newCategories
        notifyDataSetChanged()
    }
}
