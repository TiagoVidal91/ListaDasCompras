package com.example.listadascompras

data class Product(val name: String, var isChecked: Boolean = false): java.io.Serializable
fun Product.toProductView() = ProductView(productName = name)

