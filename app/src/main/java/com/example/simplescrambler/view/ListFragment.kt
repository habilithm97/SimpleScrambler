package com.example.simplescrambler.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simplescrambler.R
import com.example.simplescrambler.adapter.ScrambleAdapter
import com.example.simplescrambler.databinding.FragmentListBinding
import com.example.simplescrambler.viewmodel.ScrambleViewModel

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val scrambleAdapter by lazy { ScrambleAdapter() }
    private val viewModel: ScrambleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        binding.apply {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext()).apply {
                    reverseLayout = true
                    stackFromEnd = true
                }
                setHasFixedSize(true) // 고정된 사이즈의 RecyclerView -> 불필요한 리소스 줄이기
                adapter = scrambleAdapter
            }
            btnDeleteAll.setOnClickListener {
                showDialog()
            }
        }
        // viewLifecyclerOwner를 관찰자로 사용 -> 프래그먼트의 뷰가 활성 상태일 때만 LiveData를 관찰하도록 보장
        // -> 메모리 릭 방지, 프래그먼트의 뷰가 파괴될 때 불필요한 업데이트를 막음
        viewModel.getAll.observe(viewLifecycleOwner, Observer {
            scrambleAdapter.submitList(it) { // 새로운 데이터 리스트를 어댑터에 제공
                binding.recyclerView.scrollToPosition(it.size - 1) // 마지막 아이템 위치로 스크롤
            }
        })

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val scramble = scrambleAdapter.currentList[position]
                viewModel.deleteScramble(scramble)
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }

    private fun showDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.delete_all)
            .setMessage(R.string.delete_all_msg)
            .setPositiveButton(R.string.yes) { diglog, _ ->
                viewModel.deleteAll()
                diglog.dismiss()
            }
            .setNegativeButton(R.string.no) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // 메모리 릭 방지
    }
}