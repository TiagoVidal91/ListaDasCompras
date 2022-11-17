package com.example.listadascompras

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ShoppingList: AppCompatActivity() {

    private lateinit var productAdapter: ProductAdapter
    private lateinit var rvProductList: RecyclerView
    private lateinit var btnSaveList: Button
    private lateinit var btnAddProduct: Button
    private lateinit var btnDeleteProduct: Button
    private lateinit var etProductName: EditText
    private lateinit var productList: ArrayList<Product>
    private lateinit var saveFile: SaveFile


    override fun onCreate(savedInstanceState: Bundle?) {

        saveFile = loadProducts(  this, "products")
        productList = saveFile.products

        super.onCreate(savedInstanceState)
        setContentView(R.layout.shopping_list)

        productAdapter = ProductAdapter(productList)

        rvProductList = findViewById(R.id.rvProductList)
        rvProductList.adapter = productAdapter

        rvProductList.layoutManager = LinearLayoutManager(this)

        btnAddProduct = findViewById(R.id.btnAddProduct)
        btnSaveList = findViewById(R.id.btnSaveList)
        etProductName = findViewById(R.id.etProductName)

        btnAddProduct.setOnClickListener {
            val productText = etProductName.text.toString()
            if (productText.isNotEmpty()) {
                val product = Product(productText, isChecked = true)
                productAdapter.addProduct(product)
                updateList()
                etProductName.text.clear()
                hideKeyboard(rvProductList)
            }
        }

        btnSaveList.setOnClickListener {
            updateList()
        }
    }

    private fun updateList(){
        val intent = Intent()
        updateList(productList,this,"products")
        val productViewList = productListToProductViewList(productList)

        intent.putExtra("products", productViewList)
        intent.putExtra("date", dateTimeToString())
        setResult(RESULT_OK, intent);
    }
}

