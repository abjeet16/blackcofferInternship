package com.example.blackcofferintern

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.blackcofferintern.databinding.ActivityRefineScreenBinding

class RefineScreen : AppCompatActivity() {
    private val binding:ActivityRefineScreenBinding by lazy {
        ActivityRefineScreenBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpDropDownList()
        setUpSeekBar()
        setUpButtons()
    }

    private fun setUpButtons() {
        //Explore Button
        binding.ExploreButton.setOnClickListener {
            // Handle the click event here
            val intent : Intent
            intent = Intent(this,exploreScreen::class.java)
            val city = binding.Loction.text
            val distance = binding.howFarEditText.text.toString()

            Log.d("city", city.toString())
            Log.d("distance", distance)

            if (city.isEmpty() || distance.isEmpty()){
                Toast.makeText(this,"Please fill all the fields", Toast.LENGTH_SHORT).show()
            }else{
                intent.putExtra("city",city)
                intent.putExtra("distance",distance)
                startActivity(intent)
            }
        }
    }

    private fun setUpSeekBar() {
        binding.SeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    // Update the EditText when the SeekBar changes (by user interaction)
                    binding.howFarEditText.setText(progress.toString())
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Optional: You can add behavior for when the user starts interacting with the SeekBar
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Optional: You can add behavior for when the user stops interacting with the SeekBar
            }
        })

        setUpEditTextForValueChange()
    }

    private fun setUpEditTextForValueChange() {
        binding.howFarEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed here
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No action needed here
            }

            override fun afterTextChanged(s: Editable?) {
                val text = s.toString()
                if (text.isNotEmpty()) {
                    try {
                        val progress = text.toInt()
                        if (progress in 0..binding.SeekBar.max) {
                            binding.SeekBar.progress = progress
                        } else {
                            binding.howFarEditText.setText(binding.SeekBar.progress.toString())
                        }
                    } catch (e: NumberFormatException) {
                        binding.howFarEditText.setText(binding.SeekBar.progress.toString())
                    }
                }
            }
        })
    }

    private fun setUpDropDownList() {
        // Sample data for the dropdown
        val items = arrayOf("Chennai", "Mumbai", "Delhi", "Bangalore", "Hyderabad", "Pune", "Kolkata", "Ahmedabad", "Jaipur", "Surat",
            "Lucknow", "Kanpur", "Nagpur", "Indore", "Thane", "Bhopal", "Visakhapatnam", "Pimpri-Chinchwad", "Patna", "Vadodara")

        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the spinner
        binding.SpinnerLoction.adapter = adapter

        // Optional: Set a listener to handle item selection
        binding.SpinnerLoction.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Handle item selection here
                val selectedItem = items[position]

                binding.Loction.text = selectedItem
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }
}