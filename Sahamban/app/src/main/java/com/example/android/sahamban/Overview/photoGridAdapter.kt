package com.example.android.sahamban.Overview


import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.sahamban.Network.SahambanProperty
import com.example.android.sahamban.databinding.GridViewItemBinding

class PhotoGridAdapter(val onClickListener: OnClickListener): ListAdapter<SahambanProperty,PhotoGridAdapter.SahambanPropertyViewHolder>(DiffCallback) {

    class SahambanPropertyViewHolder(private val binding: GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(sahambanProperty: SahambanProperty) {
            binding.property = sahambanProperty
            binding.executePendingBindings()
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<SahambanProperty>() {
        override fun areItemsTheSame(oldItem: SahambanProperty, newItem: SahambanProperty,
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: SahambanProperty, newItem: SahambanProperty,
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SahambanPropertyViewHolder {
        return SahambanPropertyViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: SahambanPropertyViewHolder, position: Int) {
        val sahambanProperty=getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(sahambanProperty)
        }
        holder.bind(sahambanProperty)
    }

    class OnClickListener(val clickListener: (sahambanProperty: SahambanProperty) -> Unit){
        fun onClick(sahambanProperty: SahambanProperty) = clickListener(sahambanProperty)
    }
}
