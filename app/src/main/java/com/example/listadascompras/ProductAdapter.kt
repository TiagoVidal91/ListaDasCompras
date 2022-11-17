package com.example.listadascompras

import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(private val productList: MutableList<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){

    class ProductViewHolder(productView: View) : RecyclerView.ViewHolder(productView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        //LayoutInflator will convert an XML file to a VIEW class.
        //R --> Resource in question
        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.product, parent, false
            )
        )
    }

    fun addProduct(product: Product){
        productList.add(product)
        notifyItemInserted(productList.size-1)
    }

    private fun removeProduct(position: Int){
        productList.removeAt(position)
        notifyItemRemoved(position)
    }

    private fun grayOutText(tvProductName: TextView, isChecked: Boolean){
        if(isChecked){
            tvProductName.setTextColor(Color.parseColor("#333333"))
        }else{
            tvProductName.setTextColor(Color.parseColor("#989898"))
        }
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currProduct = productList[position]
        holder.itemView.apply {
            holder.itemView.findViewById<TextView>(R.id.tvProductName).text = currProduct.name
            holder.itemView.findViewById<CheckBox>(R.id.cbAdd).isChecked   = currProduct.isChecked
            grayOutText(holder.itemView.findViewById<TextView>(R.id.tvProductName),currProduct.isChecked)
            holder.itemView.findViewById<CheckBox>(R.id.cbAdd).setOnCheckedChangeListener { _, checked ->
                grayOutText(holder.itemView.findViewById<TextView>(R.id.tvProductName),checked)
                currProduct.isChecked = !currProduct.isChecked
            }
        }
        holder.itemView.findViewById<Button>(R.id.btnDelete).setOnClickListener {
            removeProduct(position)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }



}