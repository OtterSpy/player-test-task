package io.otterspy.playertesttask.presentation.ui.fragments.videolistsfragment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.otterspy.playertesttask.databinding.HolderGridListBinding
import io.otterspy.playertesttask.domain.model.Item
import io.otterspy.playertesttask.presentation.ui.fragments.videolistsfragment.adapters.holders.GridListViewHolder

class GridListAdapter : ListAdapter<Item, RecyclerView.ViewHolder>(Companion) {

    private var onItemClickListener: ((Item) -> Unit)? = null

    fun setOnItemClickListener(listener: (Item) -> Unit) {
        onItemClickListener = listener
    }

    companion object : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.snippet.channelId == newItem.snippet.channelId
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GridListViewHolder(
            HolderGridListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is GridListViewHolder -> {
                getItem(position).let { item ->
                    holder.bind(item)
                    holder.binding.gridListCardView.setOnClickListener {
                        onItemClickListener?.invoke(item)
                    }
                }
            }
        }
    }
}