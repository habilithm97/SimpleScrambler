package com.example.cubescrambler2404

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cubescrambler2404.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        binding.apply {
            rv.layoutManager = LinearLayoutManager(context)
            //rv.adapter = RvAdapter(itemList)
        }
    }
}