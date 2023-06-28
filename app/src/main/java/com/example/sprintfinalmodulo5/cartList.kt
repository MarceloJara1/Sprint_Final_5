package com.example.sprintfinalmodulo5

data class Product(val name: String, val url: String, val price: Double)

class cartList {

    private val productList: MutableList<Product> = mutableListOf()

    fun addProduct(name: String, url: String, price: Double) {
        productList.add(Product(name, url, price))
    }

    fun getProductList(): MutableList<Product> {
        return productList
    }

    fun deleteProduct(name: String) {
        val product = productList.find { it.name == name }
        productList.remove(product)
    }

    fun clear(){
        productList.clear()
    }

}
