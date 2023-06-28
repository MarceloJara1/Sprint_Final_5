package com.example.sprintfinalmodulo5.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sprintfinalmodulo5.R
import com.example.sprintfinalmodulo5.model.Shoes

class ShoesAdapter(private val shoeList: List<Shoes>): RecyclerView.Adapter<ShoesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ShoesViewHolder(layoutInflater.inflate(R.layout.grid_item, parent, false))
    }

    override fun getItemCount(): Int = shoeList.size

    override fun onBindViewHolder(holder: ShoesViewHolder, position: Int) {
        val item = shoeList[position]
        holder.render(item)
    }
}