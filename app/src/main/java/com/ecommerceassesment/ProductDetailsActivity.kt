package com.ecommerceassesment

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ecommerceassesment.databinding.ActivityHomeBinding
import com.ecommerceassesment.databinding.ActivityProductDetailsBinding

class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailsBinding
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()

    }

    private fun initViews() {

        binding.imgMinus.setOnClickListener {
            if(count == 0){
               return@setOnClickListener
            }
            count--
            binding.tvCount.text= count.toString()
        }

        binding.imgPlus.setOnClickListener {
            count++
            binding.tvCount.text= count.toString()
        }
    }
}