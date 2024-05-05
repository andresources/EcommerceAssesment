package com.ecommerceassesment

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ecommerceassesment.databinding.ItemProductListBinding

class ProductListAdapter(private val productListPojo: ArrayList<ProductListPojo>) :
    RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {
    private lateinit var binding: ItemProductListBinding


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemProductListBinding.inflate(layoutInflater, parent, false)
        return ProductViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val data = productListPojo[position]
        holder.bindData(productListPojo[position], position)

        holder.itemView.setOnClickListener {

            val context = holder.itemView.context
            val intent = Intent(context, ProductDetailsActivity::class.java).apply {
                putExtra("pname",data.name)
                putExtra("pdes",data.description)
            }
            context.startActivity(intent)
        }
    }

    class ProductViewHolder(private val localBinding: ItemProductListBinding) :
        RecyclerView.ViewHolder(localBinding.root) {
        private lateinit var databaseHelper: DataBaseHelper
        fun bindData(productListPojo: ProductListPojo, position: Int) {
            with(localBinding) {
                var count = 1
                title.text = productListPojo.name
                tvProductDescription.text = productListPojo.description
                tvAddtoCart.setOnClickListener {
                    databaseHelper = DataBaseHelper(it.context)
                    databaseHelper.delete(productListPojo.name)
                    databaseHelper.insertData(productListPojo.name,productListPojo.description,1,productListPojo.price.toInt())
                    llCart.visibility = View.VISIBLE
                    tvAddtoCart.visibility = View.GONE
                }
                imgPlus.setOnClickListener {
                    count++
                    tvCount.text = "$count"
                    databaseHelper = DataBaseHelper(it.context)
                    databaseHelper.update(productListPojo.name,count)
                }
                imgMinus.setOnClickListener {
                    if(count>0){
                        count--
                        tvCount.text = "$count"
                        databaseHelper = DataBaseHelper(it.context)
                        databaseHelper.update(productListPojo.name,count)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = productListPojo.size
}
