package com.example.cubescrambler2404

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cubescrambler2404.databinding.ItemRvBinding

class RvAdapter : ListAdapter<Scramble, RvAdapter.MyViewHolder>(diffCallback) {

    inner class MyViewHolder(private val binding: ItemRvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(scramble: Scramble) {
            binding.apply {
                tvScramble.text = scramble.scramble
                tvDate.text = scramble.date
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Scramble>() {
            override fun areItemsTheSame(oldItem: Scramble, newItem: Scramble): Boolean {
                return oldItem.id == newItem.id // 두 아이템이 같은지
            }
            override fun areContentsTheSame(oldItem: Scramble, newItem: Scramble): Boolean {
                return oldItem == newItem // 두 아이템의 데이터가 같은지
            }
        }
    }
}