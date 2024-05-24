package com.example.blackcofferintern

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blackcofferintern.DataClass.exploreItemDataClass
import com.example.blackcofferintern.adapter.exploreDataAdapter
import com.example.blackcofferintern.databinding.FragmentExploreBinding
import kotlin.random.Random

class exploreScreen : AppCompatActivity() {

    private val binding: FragmentExploreBinding by lazy {
        FragmentExploreBinding.inflate(layoutInflater)
    }
    private lateinit var adapter: exploreDataAdapter
    private val listOfPeople = mutableListOf<exploreItemDataClass>()
    private val FliteredlistOfPeople = mutableListOf<exploreItemDataClass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        showAllPeopleInRecyclerView()
        searnchPanel()

    }
    private fun showAllPeopleInRecyclerView() {

        //All this demo data can be changed with actual data from the database
        //Array Random of Names
        val names = arrayOf(
            "Aarav", "Vivaan", "Aditya", "Vihaan", "Arjun", "Sai", "Reyansh", "Ayaan", "Krishna", "Ishaan",
            "Shaurya", "Atharv", "Arnav", "Aarush", "Dhruv", "Kabir", "Ritvik", "Aradhya", "Rudra", "Anay",
            "Nirvaan", "Divit", "Aadiv", "Rohan", "Pranav", "Darsh", "Yuvaan", "Agastya", "Ved", "Laksh",
            "Raghav", "Ivaan", "Dev", "Neel", "Aryan", "Samarth", "Ranveer", "Hridaan", "Shivansh", "Tanay",
            "Nivaan", "Veer", "Param", "Tanish", "Shaarav", "Vivaan", "Vihaan", "Ishaan", "Arnav", "Rudra",
            "Rohan", "Aarav", "Kian", "Reyansh", "Advik", "Aarush", "Advait", "Ritvik", "Kabir", "Ayaan",
            "Krish", "Aayansh", "Shreyansh", "Atharv", "Arin", "Ahaan", "Rayan", "Vivaan", "Yash", "Sai",
            "Vihaan", "Arjun", "Kartik", "Dhruv", "Aryan", "Arnav", "Aditya", "Laksh", "Vivaan", "Vedansh",
            "Samar", "Reyansh", "Ishaan", "Arham", "Aarush", "Shaurya", "Arin", "Ansh", "Rudra", "Ayansh",
            "Kiaan", "Shivansh", "Yuvraj", "Darsh", "Agastya", "Raghav", "Hriday"
        )
        //Array Random of Cities
        val cities = arrayOf(
            "Chennai", "Mumbai", "Delhi", "Bangalore", "Hyderabad", "Pune", "Kolkata", "Ahmedabad", "Jaipur", "Surat",
            "Lucknow", "Kanpur", "Nagpur", "Indore", "Thane", "Bhopal", "Visakhapatnam", "Pimpri-Chinchwad", "Patna", "Vadodara"
        )

      // Array of Random hobbies for diversity
        val hobbies = arrayOf(
            "basketball | Coding | Gaming", "Reading | Music | Travelling", "Cooking | Dancing | Photography",
            "Sports | Movies | Hiking", "Gaming | Coding | Reading", "Yoga | Gardening | Painting",
            "Cycling | Swimming | Running", "Writing | Singing | Drawing", "Travelling | Cooking | Coding",
            "Photography | Music | Dancing"
        )
        //Array of Image Resources
        val imageResources = arrayOf(
            R.drawable.randomimage1, R.drawable.randomimage2, R.drawable.randomimage3,
            R.drawable.randomimage4,)

  // Loop to add 100 entries to the list
        for (i in 0 until 100) {
            val imageindex = Random.nextInt(0, 4)
            val randomDistance = Random.nextInt(0, 101)
            listOfPeople.add(
                exploreItemDataClass(
                    Name = names[i % names.size], // Cycle through names array
                    city = cities[i % cities.size], // Cycle through cities array
                    Loction = randomDistance, // Increment distance by 10 for each entry
                    Image = imageResources[imageindex], // Placeholder image resource
                    Hobby = hobbies[i % hobbies.size], // Cycle through hobbies array
                    introduction = "Hi, I am open to being Friends" // Static message
                )
            )
        }

        FilterPeopleOnBasisOfRefine()

    }

    private fun FilterPeopleOnBasisOfRefine() {

        val city = intent.getStringExtra("city")
        val distance = intent.getStringExtra("distance")
        val distanceInt = distance?.toInt()
        for (i in listOfPeople){
            if (i.city == city && i.Loction < distanceInt!!){
                FliteredlistOfPeople.add(i)
            }
        }
        val filteredMenuItem = ArrayList(FliteredlistOfPeople)
        setAdapter(filteredMenuItem)
    }

    private fun setAdapter(filteredMenuItem: List<exploreItemDataClass>) {
        adapter = exploreDataAdapter(filteredMenuItem)
        binding.reyclerView.adapter = adapter
        binding.reyclerView.layoutManager = LinearLayoutManager(this)
    }
    fun searnchPanel(){
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                filterMenuitem(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterMenuitem(newText)
                return true
            }
        })
    }
    fun filterMenuitem(query: String) {
        val filteredMenuItem = FliteredlistOfPeople.filter {
            it.Name.contains(query, ignoreCase = true) == true
        }
        setAdapter(filteredMenuItem)
    }
}