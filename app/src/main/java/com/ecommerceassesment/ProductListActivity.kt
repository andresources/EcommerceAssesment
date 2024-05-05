package com.ecommerceassesment

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ecommerceassesment.databinding.ActivityProductListBinding

class ProductListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductListBinding
    private var productListPojo = ArrayList<ProductListPojo>()
    private lateinit var databaseHelper: DataBaseHelper
    var data: List<ProductDBPojo> = ArrayList<ProductDBPojo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initDataBase()

        binding.rvLIstOfItems.layoutManager = LinearLayoutManager(this)
        binding.rvLIstOfItems.adapter = ProductListAdapter(productListItems())
    }

    private fun initDataBase() {
        databaseHelper = DataBaseHelper(this)
    }

    private fun initViews() {
        // Inserting Products From Local Database
        // This is just checking weather data base values are coming or not
        binding.tvToolbar.setOnClickListener {
            data = databaseHelper.readData()
            data.forEach { Toast.makeText(this@ProductListActivity, "List Of Product Details ${it.name}", Toast.LENGTH_SHORT).show() }
        }

    }

    private fun productListItems(): ArrayList<ProductListPojo> {
        productListPojo = ArrayList<ProductListPojo>()
        productListPojo.add(
            ProductListPojo(
                "Real Me Narzo 50",
                "This is Smart Phone",
                "200",
                "2"
            )
        )
        productListPojo.add(
            ProductListPojo(
                "One Plus 12R",
                "This is Smart Phone",
                "200",
                "2"
            )
        )
        productListPojo.add(
            ProductListPojo(
                "Samsung Note 24",
                "This is Smart Phone",
                "200",
                "2"
            )
        )
        productListPojo.add(ProductListPojo("Oppo", "This is Smart Phone", "200", "2"))


        return productListPojo

    }
}