package com.example.sprintfinalmodulo5.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sprintfinalmodulo5.Product
import com.example.sprintfinalmodulo5.databinding.CartItemLayoutBinding

class CartViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

    private val binding = CartItemLayoutBinding.bind(view)

    fun render(product: Product) {

        binding.txtProductName.text = product.name
        binding.txtProductPrice.text = String.format("%.2f", product.price)
        Glide.with(binding.imgViewCart.context).load(product.url).into(binding.imgViewCart)

    }
    fun deleteProduct(cartAdapter: CartAdapter, position: Int) {
        binding.iconButton.setOnClickListener {
            cartAdapter.deleteProduct(position)
            cartAdapter.calculateTotalPrice()

        }
    }





}