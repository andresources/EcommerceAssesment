package com.ecommerceassesment

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ecommerceassesment.databinding.ItemHomeBinding
import com.ecommerceassesment.databinding.ItemProductDetailsBinding

class ProductDetailsAdapter(private val productDetailsPojo: ArrayList<ProductDetailsPojo>) :
    RecyclerView.Adapter<ProductDetailsAdapter.ProductViewHolder>() {
    private lateinit var binding: ItemProductDetailsBinding



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemProductDetailsBinding.inflate(layoutInflater, parent, false)
        return ProductViewHolder(binding)
    }
    private lateinit var databaseHelper: DataBaseHelper
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val data = productDetailsPojo[position]
        holder.bindData(productDetailsPojo[position], position)
        holder.itemView.setOnClickListener {
            databaseHelper = DataBaseHelper(it.context)
            //databaseHelper.insertData(productDetailsPojo[position].name)
            Toast.makeText(it.context,"${productDetailsPojo[position].name} Product added successfully to the cart",Toast.LENGTH_SHORT).show()
        }
    }

    class ProductViewHolder(private val localBinding: ItemProductDetailsBinding) :
        RecyclerView.ViewHolder(localBinding.root) {
        fun bindData(productDetailsPojo: ProductDetailsPojo, position: Int) {
            with(localBinding) {
                localBinding.title.text = productDetailsPojo.name
            }
        }
    }

    override fun getItemCount(): Int = productDetailsPojo.size
}
