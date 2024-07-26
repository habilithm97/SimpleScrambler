package com.example.cubescrambler2404

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cubescrambler2404.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private val rvAdapter by lazy { RvAdapter() }
    private val viewModel: MyViewModel by viewModels()

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
            rv.apply {
                layoutManager = LinearLayoutManager(requireContext()).apply {
                    reverseLayout = true
                    stackFromEnd = true
                }
                setHasFixedSize(true) // 고정된 사이즈의 RecyclerView -> 불필요한 리소스 줄이기
                adapter = rvAdapter
            }
        }
        viewModel.getAll.observe(viewLifecycleOwner, Observer { itemList -> // LiveData를 관찰할 때는 viewLifecycleOwner 사용
            rvAdapter.setItem(itemList)
        })
    }
}