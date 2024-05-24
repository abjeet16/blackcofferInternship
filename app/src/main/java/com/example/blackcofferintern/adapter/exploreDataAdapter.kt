package com.example.blackcofferintern.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blackcofferintern.DataClass.exploreItemDataClass
import com.example.blackcofferintern.databinding.ExploreItemLayoutBinding

class exploreDataAdapter(private val listOfPeople: List<exploreItemDataClass>): RecyclerView.Adapter<exploreDataAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding:ExploreItemLayoutBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.ProfileImage.setImageResource(listOfPeople[position].Image)
            binding.Name.text = listOfPeople[position].Name
            binding.Location.text = listOfPeople[position].Loction
            binding.Skills.text = listOfPeople[position].Hobby
            binding.Introduction.text = listOfPeople[position].introduction
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): exploreDataAdapter.ViewHolder {
        val binding = ExploreItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: exploreDataAdapter.ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = listOfPeople.size
}