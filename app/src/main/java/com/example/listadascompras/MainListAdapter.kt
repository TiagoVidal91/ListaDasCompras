package com.example.listadascompras

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.green
import androidx.core.graphics.toColor
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton


class MainListAdapter(private val productViewList: MutableList<ProductView>): RecyclerView.Adapter<MainListAdapter.MainListViewHolder>() {

    class MainListViewHolder(mainListView: View) : RecyclerView.ViewHolder(mainListView)

    fun updateProductList(newList: ArrayList<ProductView>){

        productViewList.clear()

        productViewList.addAll(newList)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainListViewHolder {
        return MainListAdapter.MainListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.product_view, parent, false
            )
        )
    }

    fun updateProductButton(button: MaterialButton){
        if(button.text == "Add"){
            button.setBackgroundColor(Color.parseColor("#2eb82e"))
            button.text = "Added"
            button.setIconResource(R.drawable.ic_product_added)
        }else{
            button.setBackgroundColor(Color.parseColor("#898989"))
            button.text = "Add"
            button.setIconResource(R.drawable.ic_add_product)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MainListViewHolder, position: Int) {
        val currProduct = productViewList[position]
        val addButton = holder.itemView.findViewById<Button>(R.id.btnAddToShoppingList)
        holder.itemView.apply {
            holder.itemView.findViewById<TextView>(R.id.tvProductViewName).text = currProduct.productName
        }
        addButton.setOnClickListener {
            updateProductButton(button = addButton as MaterialButton)
        }
    }

    override fun getItemCount(): Int {
        return productViewList.size
    }

}