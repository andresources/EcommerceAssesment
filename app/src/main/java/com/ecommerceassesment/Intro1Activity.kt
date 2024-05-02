package com.ecommerceassesment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ecommerceassesment.databinding.ActivityIntro1Binding

class Intro1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityIntro1Binding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntro1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences(PrefConstant.PREF_FILE_NAME, Context.MODE_PRIVATE)
        initiViews()
    }
    private fun initiViews() {
        with(binding){
            val value = sharedPreferences.getString(PrefConstant.IS_INTRO_VISITED, "No")
            if (value == "Yes") {
                jumpLoginScreen()
            }
            btnNext.setOnClickListener {
                startActivity(Intent(this@Intro1Activity,Intro2Activity::class.java))
                finish()
            }
            tvSkip.setOnClickListener {
                sharedPreferences.edit().putString(PrefConstant.IS_INTRO_VISITED, "Yes").apply()
                startActivity(Intent(this@Intro1Activity,LoginActivity::class.java))
                finish()
            }

        }
    }
    private fun jumpLoginScreen() {
        sharedPreferences = getSharedPreferences(
            PrefConstant.PREF_FILE_NAME,
            Context.MODE_PRIVATE
        )
        startActivity(Intent(this,LoginActivity::class.java))
    }
}