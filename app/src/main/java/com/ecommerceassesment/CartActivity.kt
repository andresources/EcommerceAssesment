package com.ecommerceassesment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.ecommerceassesment.databinding.ActivityCartBinding
import com.ecommerceassesment.databinding.ActivityHomeBinding

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var databaseHelper: DataBaseHelper
    private var productDBPojo : ArrayList<ProductDBPojo> = ArrayList<ProductDBPojo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initDataBase()
        binding.rvLIstOfItems.layoutManager = LinearLayoutManager(this)
        binding.rvLIstOfItems.adapter = CartAdapter(dataDbProducts())

    }

    private fun initDataBase() {
        databaseHelper = DataBaseHelper(this)
    }

    fun dataDbProducts() : ArrayList<ProductDBPojo>{
        productDBPojo = ArrayList<ProductDBPojo>()
        databaseHelper.readData().forEach(){
            productDBPojo.add(ProductDBPojo(0,it.name))
        }

        return productDBPojo
    }
}