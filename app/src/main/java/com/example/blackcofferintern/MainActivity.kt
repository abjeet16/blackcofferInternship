package com.example.blackcofferintern

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.blackcofferintern.adapter.ViewPagerAdapter
import com.example.blackcofferintern.databinding.ActivityMainBinding
import com.example.blackcofferintern.fragment.Explore_Fragment
import com.example.blackcofferintern.fragment.Refine_Fragment

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val fragmentArrayList = ArrayList<Fragment>()
        fragmentArrayList.add(Refine_Fragment())
        fragmentArrayList.add(Explore_Fragment())

        val adapter = ViewPagerAdapter(this,supportFragmentManager,fragmentArrayList)

        binding.ViewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.ViewPager)
    }
}