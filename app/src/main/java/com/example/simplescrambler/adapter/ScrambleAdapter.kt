package com.example.simplescrambler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.simplescrambler.databinding.ItemScrambleBinding
import com.example.simplescrambler.room.Scramble

class ScrambleAdapter : ListAdapter<Scramble, ScrambleAdapter.ScrambleViewHolder>(diffCallback) {

    inner class ScrambleViewHolder(private val binding: ItemScrambleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(scramble: Scramble) {
            binding.apply {
                tvScramble.text = scramble.scramble
                tvDate.text = scramble.date
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScrambleViewHolder {
        val binding = ItemScrambleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScrambleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScrambleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Scramble>() {
            override fun areItemsTheSame(oldItem: Scramble, newItem: Scramble): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: Scramble, newItem: Scramble): Boolean {
                return oldItem == newItem
            }
        }
    }
}