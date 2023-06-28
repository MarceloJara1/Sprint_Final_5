package com.example.sprintfinalmodulo5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sprintfinalmodulo5.adapter.ShoesAdapter
import com.example.sprintfinalmodulo5.databinding.FragmentItemProductsBinding
import com.example.sprintfinalmodulo5.model.ShoesProvider

class itemProducts : Fragment() {

    private lateinit var binding: FragmentItemProductsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentItemProductsBinding.inflate(inflater, container, false)

        binding.recyclerItem.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerItem.adapter = ShoesAdapter(ShoesProvider.shoeList)


        return binding.root
    }

}