package com.ecommerceassesment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ecommerceassesment.databinding.ItemCartBinding

class CartAdapter(private val productDBPojo: ArrayList<ProductDBPojo>,private val onClicked: ()-> Unit) :
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
        holder.bindData(productDBPojo[position], position,onClicked)
    }

    class ProductViewHolder(private val localBinding: ItemCartBinding) :
        RecyclerView.ViewHolder(localBinding.root) {
        private lateinit var databaseHelper: DataBaseHelper
        fun bindData(productListPojo: ProductDBPojo, position: Int,onClicked: ()-> Unit) {
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
                    onClicked.invoke()
                }
                imgMinus.setOnClickListener {
                    if(count>0){
                        count--
                        pcost.text ="$ ${count*productListPojo.pcost}"
                        tvCount.text = "$count"
                        databaseHelper = DataBaseHelper(it.context)
                        databaseHelper.update(productListPojo.name,count)
                        onClicked.invoke()
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = productDBPojo.size
}
