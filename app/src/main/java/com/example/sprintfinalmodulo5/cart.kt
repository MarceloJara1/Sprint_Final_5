package com.example.sprintfinalmodulo5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sprintfinalmodulo5.adapter.CartAdapter
import com.example.sprintfinalmodulo5.databinding.FragmentCartBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar


class cart : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false)

        recyclerView = binding.recyclerItemCart
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = CartAdapter(requireContext(), getCartList(), ::updateTotalPrice)
        recyclerView.adapter = adapter



        binding.btnVaciar.setOnClickListener {

            if(getCartList().isNotEmpty()){

                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(("Vaciar carrito"))
                    .setMessage(("Esta seguro que desea vaciar el carrito"))

                    .setNegativeButton("Cancelar") { dialog, which ->

                    }
                    .setPositiveButton("Aceptar") { dialog, which ->
                        clearCart()
                        adapter.submitList(getCartList())
                        adapter.notifyDataSetChanged()
                        updateTotalPrice()
                    }
                    .show()
            }else{
                Snackbar.make(binding.root, "El carrito esta vacio", Snackbar.LENGTH_SHORT)

                    .show()
            }
            }
        updateTotalPrice()

        return binding.root
    }

    private fun getCartList(): MutableList<Product>{
        val sharedPreferencesManager = sharedPreferencesManager(requireContext())
        val cartList = sharedPreferencesManager.getCartList()
        return cartList.getProductList()
    }

    private fun clearCart(){
        val sharedPreferencesManager = sharedPreferencesManager(requireContext())
        val cartList = sharedPreferencesManager.getCartList()
        sharedPreferencesManager.clearCart()
        cartList.clear()

    }

    private fun updateTotalPrice(){
        val totalPrice = adapter.calculateTotalPrice()
        binding.txtTotal.text = "Total = ${String.format("%.2f", totalPrice)}"
    }
}