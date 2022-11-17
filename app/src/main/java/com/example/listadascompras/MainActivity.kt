package com.example.listadascompras

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvShoppingList: RecyclerView
    private lateinit var bEditShoppingList: Button
    private lateinit var llMainPage: LinearLayout
    private lateinit var tvListUpdated: TextView
    private lateinit var mainListAdapter: MainListAdapter
    private lateinit var productList: ArrayList<ProductView>
    private lateinit var saveFile: SaveFile

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            if (data != null) {
                productList = data.getSerializableExtra("products") as ArrayList<ProductView>
                mainListAdapter.updateProductList(productList)
                tvListUpdated.text = data.getStringExtra("date")
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        saveFile = loadProducts(this, "products")
        productList = productListToProductViewList(saveFile.products)
        mainListAdapter = MainListAdapter(productList)
        rvShoppingList = findViewById(R.id.rvMainShoppingList)
        bEditShoppingList = findViewById(R.id.bEditShoppingList)
        llMainPage = findViewById(R.id.llMainPage)
        tvListUpdated = findViewById(R.id.tvListUpdated)

        rvShoppingList.adapter = mainListAdapter

        rvShoppingList.layoutManager = LinearLayoutManager(this)

        tvListUpdated.text = saveFile.date

        bEditShoppingList.setOnClickListener {
            val intent = Intent(this, ShoppingList::class.java)
            resultLauncher.launch(intent)
        }
    }

}