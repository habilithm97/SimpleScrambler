package com.example.cubescrambler2404

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cubescrambler2404.databinding.ItemRvBinding

class RvAdapter : RecyclerView.Adapter<RvAdapter.MyViewHolder>() {
    private var itemList: List<Scramble> = emptyList()

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
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setItem(itemList: List<Scramble>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

    fun getPosition(position: Int): Scramble {
        return itemList[position]
    }
}