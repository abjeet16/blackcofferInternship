package com.example.blackcofferintern.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blackcofferintern.DataClass.exploreItemDataClass
import com.example.blackcofferintern.R
import com.example.blackcofferintern.adapter.exploreDataAdapter
import com.example.blackcofferintern.databinding.FragmentExploreBinding

class Explore_Fragment : Fragment() {

    private lateinit var binding:FragmentExploreBinding
    private lateinit var adapter:exploreDataAdapter
    private val listOfPeople = mutableListOf<exploreItemDataClass>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExploreBinding.inflate(layoutInflater)

        showAllPeopleInRecyclerView()
        searnchPanel()

        return binding.root
    }

    private fun showAllPeopleInRecyclerView() {
        listOfPeople.add(exploreItemDataClass("abjeet",
            "Mysuru,within 1km",
            R.drawable.randomimage1,
            "basketball | Coding | Gaming",
            "Hi iam open to being Friends"))

        listOfPeople.add(exploreItemDataClass("Sujeet",
            "Mysuru,within 1km",
            R.drawable.randomimage2,
            "basketball | Coding | Gaming",
            "Hi iam open to being Friends"))

        listOfPeople.add(exploreItemDataClass("Rahul",
            "Mysuru,within 1km",
            R.drawable.randomimage3,
            "basketball | Coding | Gaming",
            "Hi iam open to being Friends"))

        val filteredMenuItem = ArrayList(listOfPeople)
        setAdapter(filteredMenuItem)
    }

    private fun setAdapter(filteredMenuItem: List<exploreItemDataClass>) {
        adapter = exploreDataAdapter(filteredMenuItem)
        binding.reyclerView.adapter = adapter
        binding.reyclerView.layoutManager = LinearLayoutManager(requireContext())
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
        val filteredMenuItem = listOfPeople.filter {
            it.Name.contains(query, ignoreCase = true) == true
        }
        setAdapter(filteredMenuItem)
    }
}