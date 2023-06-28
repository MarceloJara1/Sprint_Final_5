package com.example.sprintfinalmodulo5.adapter

import android.view.View
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sprintfinalmodulo5.model.Shoes
import com.example.sprintfinalmodulo5.databinding.GridItemBinding
import com.example.sprintfinalmodulo5.itemProductsDirections

class ShoesViewHolder (private val view: View):RecyclerView.ViewHolder(view){

    private val binding = GridItemBinding.bind(view)

    fun render(shoe: Shoes) {
        binding.txtNombre.text = shoe.name
        binding.txtPrecio.text = shoe.price.toString()
        Glide.with(binding.imgView.context).load(shoe.url).into(binding.imgView)

        itemView.setOnClickListener {
            val action = itemProductsDirections.actionItemProductsToItemSingleProduct(shoe.name, shoe.price.toFloat(), shoe.url)
            Navigation.findNavController(view).navigate(action)

        }
    }
}