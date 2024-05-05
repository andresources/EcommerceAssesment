package com.ecommerceassesment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ecommerceassesment.databinding.ItemCartBinding

class CartAdapter(private val productDBPojo: ArrayList<ProductDBPojo>) :
    RecyclerView.Adapter<CartAdapter.ProductViewHolder>() {
    private lateinit var binding: ItemCartBinding


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemCartBinding.inflate(layoutInflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val data = productDBPojo[position]
        holder.bindData(productDBPojo[position], position)
    }

    class ProductViewHolder(private val localBinding: ItemCartBinding) :
        RecyclerView.ViewHolder(localBinding.root) {

        fun bindData(productListPojo: ProductDBPojo, position: Int) {
            with(localBinding) {
                localBinding.title.text = productListPojo.name

            }
        }
    }

    override fun getItemCount(): Int = productDBPojo.size
}
