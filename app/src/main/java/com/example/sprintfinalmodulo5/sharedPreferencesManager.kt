package com.example.sprintfinalmodulo5

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class sharedPreferencesManager(context: Context) {

    companion object{
        private const val SHARED_PREFERENCES_NAME = "PreferencesCart"
        private const val KEY_CART_LIST = "cartList"
    }

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }


    fun saveCartList(cartList: cartList) {
        val productListJson = Gson().toJson(cartList.getProductList())
        sharedPreferences.edit().putString(KEY_CART_LIST, productListJson).apply()
    }

    fun getCartList():cartList{
        val productListJson = sharedPreferences.getString(KEY_CART_LIST, null)
        return if (!productListJson.isNullOrEmpty()) {
            val type = object : TypeToken<List<Product>>() {}.type
            val productList = Gson().fromJson<List<Product>>(productListJson, type)
            cartList().apply {
                getProductList().addAll(productList)
            }
        } else {
            cartList()
        }
    }

    fun clearCart() {
        sharedPreferences.edit().remove(KEY_CART_LIST).apply()
    }

    }
