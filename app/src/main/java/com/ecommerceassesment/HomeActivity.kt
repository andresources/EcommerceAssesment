package com.ecommerceassesment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.ecommerceassesment.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews(){
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.baseline_home_24)
        }
        binding.navView.setNavigationItemSelectedListener {menuIteims ->
            menuIteims.isChecked = true
            when(menuIteims.itemId){
                R.id.home -> showToast(getString(R.string.home))
                R.id.cart -> showToast(getString(R.string.cart))
                R.id.orders -> showToast(getString(R.string.orders))
                R.id.profile -> showToast(getString(R.string.profile))
                R.id.logout -> showToast(getString(R.string.logout))
            }
            true
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            showToast("Hello")
            if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }else{
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}