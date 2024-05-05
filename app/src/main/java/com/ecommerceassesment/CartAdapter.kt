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
        private lateinit var databaseHelper: DataBaseHelper
        fun bindData(productListPojo: ProductDBPojo, position: Int) {
            with(localBinding) {
                title.text = productListPojo.name
                description.text= productListPojo.des
                tvUnit.text= "Unit Price:$ ${productListPojo.pcost}"

                var count = productListPojo.pcount
                tvCount.text= count.toString()
                pcost.text ="$ ${count*productListPojo.pcost}"
                imgPlus.setOnClickListener {
                    count++
                    tvCount.text = "$count"
                    pcost.text ="$ ${count*productListPojo.pcost}"
                    databaseHelper = DataBaseHelper(it.context)
                    databaseHelper.update(productListPojo.name,count)
                }
                imgMinus.setOnClickListener {
                    if(count>0){
                        count--
                        pcost.text ="$ ${count*productListPojo.pcost}"
                        tvCount.text = "$count"
                        databaseHelper = DataBaseHelper(it.context)
                        databaseHelper.update(productListPojo.name,count)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = productDBPojo.size
}
