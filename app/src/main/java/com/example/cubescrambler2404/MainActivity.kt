package com.example.cubescrambler2404

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cubescrambler2404.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        binding.viewPager.adapter = ViewPagerAdapter(this@MainActivity)
    }
}