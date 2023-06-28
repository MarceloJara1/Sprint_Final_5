package com.example.sprintfinalmodulo5.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sprintfinalmodulo5.Product
import com.example.sprintfinalmodulo5.R
import com.example.sprintfinalmodulo5.sharedPreferencesManager

class CartAdapter (private val context: Context,
                   private val productList: MutableList<Product>,
                   private val totalPriceListener: () -> Unit
                ): RecyclerView.Adapter<CartViewHolder>(){

    private var totalPrice: Double = 0.0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CartViewHolder(layoutInflater.inflate(R.layout.cart_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val product = productList[position]
        holder.render(product)

        holder.deleteProduct(this, position)

    }

    override fun getItemCount(): Int = productList.size

    fun deleteProduct(position: Int) {
        val productToDelete = productList[position]

        // Eliminar el producto del SharedPreferences
        val sharedPreferencesManager = sharedPreferencesManager(context)
        val cartList = sharedPreferencesManager.getCartList()
        cartList.deleteProduct(productToDelete.name)
        sharedPreferencesManager.saveCartList(cartList)

        // Eliminar el producto del productList
        productList.removeAt(position)

        notifyItemRemoved(position)
        notifyItemRangeChanged(position, productList.size)

        totalPriceListener.invoke()

    }

    fun submitList(newList: List<Product>) {
        productList.clear()
        productList.addAll(newList)
        notifyDataSetChanged()
    }

    fun calculateTotalPrice(): Double {
        totalPrice = 0.0
        for (product in productList) {
            totalPrice += product.price
        }
        return totalPrice
    }


}