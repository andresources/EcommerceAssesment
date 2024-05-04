package com.ecommerceassesment

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ecommerceassesment.databinding.ActivityProductDetailsBinding

class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailsBinding
    private var productDetailsPojo = ArrayList<ProductDetailsPojo>()
    private lateinit var databaseHelper: DataBaseHelper
    var data: List<ProductDBPojo> = ArrayList<ProductDBPojo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initDataBase()

        binding.rvLIstOfItems.layoutManager = LinearLayoutManager(this)
        binding.rvLIstOfItems.adapter = ProductDetailsAdapter(productListItems())
    }

    private fun initDataBase() {
        databaseHelper = DataBaseHelper(this)
    }

    private fun initViews() {
        // Inserting Products From Local Database
        // This is just checking weather data base values are coming or not
        binding.tvToolbar.setOnClickListener() {
            data = databaseHelper.readData()
            data.forEach() { Toast.makeText(this@ProductDetailsActivity, "List Of Product Details ${it.name}", Toast.LENGTH_SHORT).show() }
        }

    }

    private fun productListItems(): ArrayList<ProductDetailsPojo> {
        productDetailsPojo = ArrayList<ProductDetailsPojo>()
        productDetailsPojo.add(
            ProductDetailsPojo(
                "Real Me Narzo 50",
                "This is Smart Phone",
                "200",
                "2"
            )
        )
        productDetailsPojo.add(
            ProductDetailsPojo(
                "One Plus 12R",
                "This is Smart Phone",
                "200",
                "2"
            )
        )
        productDetailsPojo.add(
            ProductDetailsPojo(
                "Samsung Note 24",
                "This is Smart Phone",
                "200",
                "2"
            )
        )
        productDetailsPojo.add(ProductDetailsPojo("Oppo", "This is Smart Phone", "200", "2"))


        return productDetailsPojo

    }
}