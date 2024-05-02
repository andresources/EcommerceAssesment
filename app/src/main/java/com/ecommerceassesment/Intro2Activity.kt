package com.ecommerceassesment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ecommerceassesment.databinding.ActivityIntro2Binding

class Intro2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityIntro2Binding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntro2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences(PrefConstant.PREF_FILE_NAME, Context.MODE_PRIVATE)
        with(binding){
            btnNext.setOnClickListener {
                startActivity(Intent(this@Intro2Activity,GetStartedActivity::class.java))
                finish()
            }
            tvSkip.setOnClickListener {
                sharedPreferences.edit().putString(PrefConstant.IS_INTRO_VISITED, "Yes").apply()
                startActivity(Intent(this@Intro2Activity,LoginActivity::class.java))
                finish()
            }
        }
    }
}