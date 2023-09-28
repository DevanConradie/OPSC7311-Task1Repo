import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.opsc7311_task1.databinding.ItemCategoryBinding  // Import the correct binding class

class CategoryDetailActivity : AppCompatActivity() {

    private lateinit var binding: ItemCategoryBinding  // Use the correct binding class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ItemCategoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Retrieve data passed from the previous activity (TimesheetActivity)
        val categoryName = intent.getStringExtra("category_name")

        // Update the UI to display category details
        binding.categoryNameTextView.text = "Category Name: $categoryName"

        // Add more code here to display other category details or perform additional actions
    }
}
