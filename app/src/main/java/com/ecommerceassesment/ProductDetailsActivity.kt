package com.ecommerceassesment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ecommerceassesment.databinding.ActivityHomeBinding
import com.ecommerceassesment.databinding.ActivityProductDetailsBinding

class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailsBinding
    lateinit var databaseHelper: DataBaseHelper
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()

    }

    private fun initViews() {
        var pname = intent.getStringExtra("pname")
        var pdes = intent.getStringExtra("pdes")
        binding.tvDescription.text = pdes
        binding.title.text = pname
        with(binding){

            imgMinus.setOnClickListener {
                if(count == 0){
                    return@setOnClickListener
                }
                count--
                binding.tvCount.text= count.toString()
            }

            imgPlus.setOnClickListener {
                count++
                binding.tvCount.text= count.toString()
            }

            var count = 1
            title.text = pname
            tvAddtoCart.setOnClickListener {
                databaseHelper = DataBaseHelper(it.context)
                databaseHelper.delete(pname!!)
                databaseHelper.insertData(pname,pdes!!,1,200)
                llCart.visibility = View.VISIBLE
                tvAddtoCart.visibility = View.GONE
            }
            imgPlus.setOnClickListener {
                count++
                tvCount.text = "$count"
                databaseHelper = DataBaseHelper(it.context)
                databaseHelper.update(pname!!,count)
            }
            imgMinus.setOnClickListener {
                if(count>0){
                    count--
                    tvCount.text = "$count"
                    databaseHelper = DataBaseHelper(it.context)
                    databaseHelper.update(pname!!,count)
                }
            }
        }
    }
}