package com.example.listadascompras

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


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

    override fun onBindViewHolder(holder: MainListViewHolder, position: Int) {
        val currProduct = productViewList[position]
        holder.itemView.apply {
            holder.itemView.findViewById<TextView>(R.id.tvProductViewName).text = currProduct.productName
        }
    }

    override fun getItemCount(): Int {
        return productViewList.size
    }

}