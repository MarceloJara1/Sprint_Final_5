package com.example.sprintfinalmodulo5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.sprintfinalmodulo5.databinding.FragmentItemSingleProductBinding


class ItemSingleProduct : Fragment() {

    private lateinit var binding: FragmentItemSingleProductBinding
    val args: ItemSingleProductArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentItemSingleProductBinding.inflate(inflater, container, false)
        val shoeName = args.Name
        val shoeUrl = args.url
        val shoePrice = args.Price
        binding.txtItemNombre.text = shoeName
        binding.txtPrecio.text = shoePrice.toString()
        Glide.with(binding.imgViewProduct.context).load(shoeUrl).into(binding.imgViewProduct)


        binding.btnAgregar.setOnClickListener {
            guardarDatos()
        }


        return binding.root
    }

    fun guardarDatos(){
        val sharedPreferencesManager = sharedPreferencesManager(requireContext())
        val cartList = sharedPreferencesManager.getCartList()
        val shoeName = args.Name
        val shoeUrl = args.url
        val shoePrice = args.Price

        cartList.addProduct(shoeName, shoeUrl, shoePrice.toDouble())

        sharedPreferencesManager.saveCartList(cartList)
    }


}