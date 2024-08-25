package com.example.simplescrambler.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.simplescrambler.adapter.ViewPagerAdapter
import com.example.simplescrambler.databinding.ActivityMainBinding

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