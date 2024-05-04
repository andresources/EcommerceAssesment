package com.ecommerceassesment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ecommerceassesment.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding


    private var listhomePojo = ArrayList<HomePojo>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()

        binding.rvLIstOfItems.layoutManager = GridLayoutManager(applicationContext, 2)
        binding.rvLIstOfItems.adapter = HomeAdapter(homeListItems())
    }

    private fun homeListItems(): ArrayList<HomePojo> {
        listhomePojo = ArrayList<HomePojo>()
        listhomePojo.add(HomePojo("Smart Phones", R.drawable.phone))
        listhomePojo.add(HomePojo("Laptops", R.drawable.laptop))
        listhomePojo.add(HomePojo("Mens Were", R.drawable.menswere))
        listhomePojo.add(HomePojo("Women's Were", R.drawable.womenswere))
        listhomePojo.add(HomePojo("Kids Were", R.drawable.kidswere))
        listhomePojo.add(HomePojo("Grocery", R.drawable.grocery))

        return listhomePojo

    }

    private fun initViews() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.baseline_home_24)
        }
        binding.navView.setNavigationItemSelectedListener { menuIteims ->
            menuIteims.isChecked = true
            when (menuIteims.itemId) {
                R.id.home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }

                R.id.cart -> showToast(getString(R.string.cart))
                R.id.orders -> showToast(getString(R.string.orders))
                R.id.profile -> showToast(getString(R.string.profile))
                R.id.logout -> showToast(getString(R.string.logout))
            }
            true
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}