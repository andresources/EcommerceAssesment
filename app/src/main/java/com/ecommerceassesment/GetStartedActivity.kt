package com.ecommerceassesment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ecommerceassesment.databinding.ActivityGetStartedBinding


class GetStartedActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityGetStartedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetStartedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences(PrefConstant.PREF_FILE_NAME, Context.MODE_PRIVATE)
        with(binding){
            btnGetStarted.setOnClickListener {
                sharedPreferences.edit().putString(PrefConstant.IS_INTRO_VISITED, "Yes").apply()
                startActivity(Intent(this@GetStartedActivity,LoginActivity::class.java))
                finish()
            }
        }
    }
}