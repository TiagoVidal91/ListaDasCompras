package com.example.listadascompras

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.*
import kotlin.collections.ArrayList

private val gson = Gson()

    fun loadProducts(context : Context, PREF_NAME : String): SaveFile {
        lateinit var productList: ArrayList<Product>

        var file: SaveFile

        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, AppCompatActivity.MODE_PRIVATE)

        val json = sharedPreferences.getString(PREF_NAME, null)

        val type: Type = object : TypeToken<SaveFile?>() {}.type

        // in below line we are getting data from gson
        // and saving it to our array list
        if(json != null){
            @Suppress("UNCHECKED_CAST")
            file = gson.fromJson<Any>(json, type) as SaveFile
            //productList = file.products
        }else{
            file = SaveFile(dateTimeToString(),arrayListOf(
                Product("Arroz",false),Product("Açucar",false),
                Product("Ten",false)))
        }
        return file
    }

    fun updateList(productListUpdate: ArrayList<Product>, context : Context, PREF_NAME : String){
        val saveFile = SaveFile(dateTimeToString(),productListUpdate)
        // method for saving the data in array list.
        // creating a variable for storing data in
        // shared preferences.
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, AppCompatActivity.MODE_PRIVATE)

        // creating a variable for editor to
        // store data in shared preferences.
        val editor = sharedPreferences.edit()

        // getting data from gson and storing it in a string.
        val json: String = gson.toJson(saveFile)

        // below line is to save data in shared
        // prefs in the form of string.
        editor.putString(PREF_NAME, json)

        // below line is to apply changes
        // and save data in shared prefs.
        editor.apply()

        // after saving data we are displaying a toast message.
        Toast.makeText(context, "A lista de produtos foi atualizada!", Toast.LENGTH_SHORT)
            .show()
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showHide(view:View) {
        view.visibility = if (view.visibility == View.VISIBLE){
            View.GONE
        } else{
            View.VISIBLE
        }
    }


    fun productListToProductViewList(productList: ArrayList<Product>): ArrayList<ProductView> {
        val viewProductList: ArrayList<ProductView> = ArrayList<ProductView>()
        productList.filter { product: Product ->  product.isChecked}
            .forEach { product ->
            viewProductList.add(product.toProductView())
            }
        return viewProductList
    }

    @SuppressLint("SimpleDateFormat")
    fun dateTimeToString(): String{
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd-MMM-yyyy HH:mm")
        return "Data da criação da lista: ${formatter.format(time)}"
    }