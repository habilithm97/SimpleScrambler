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
        val itemList = listOf(
            Scramble("D' F2 D R2 B2 R2 D' U' L2 F2 R B' F' U2 R2 F D' L R2 U'", "2407231240"),
            Scramble("R2 B' R' B2 D2 B' D2 R2 D2 B2 F' U2 R2 B' L' U L' R' D' B D2", "2407231304"),
            Scramble("R B2 R' D2 L B2 R2 F2 R' D2 L2 F' R' U2 L' B' D' U L' B R", "2407231242"),
            Scramble("L D B' U2 R2 B' F' U2 F L2 D2 R2 F D' R B' F' U L' R'", "2407231243")
        )

        binding.apply {
            rv.layoutManager = LinearLayoutManager(context)
            rv.adapter = RvAdapter(itemList)
        }
    }
}