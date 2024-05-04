package com.ecommerceassesment

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.ecommerceassesment.databinding.ItemHomeBinding

class HomeAdapter(private val homePojo: ArrayList<HomePojo>) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    private lateinit var binding: ItemHomeBinding


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeAdapter.HomeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemHomeBinding.inflate(layoutInflater, parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeAdapter.HomeViewHolder, position: Int) {
        val data = homePojo[position]
        holder.bindData(homePojo[position], position)
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, ProductDetailsActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = homePojo.size

    class HomeViewHolder(private val localBinding: ItemHomeBinding) :
        RecyclerView.ViewHolder(localBinding.root) {
        fun bindData(homePojo: HomePojo, position: Int) {
            with(localBinding) {
                localBinding.title.text = homePojo.name
                localBinding.ivImage.setImageResource(homePojo.image)
            }

        }
    }
}
