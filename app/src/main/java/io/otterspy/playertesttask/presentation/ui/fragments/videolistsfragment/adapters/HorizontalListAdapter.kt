package io.otterspy.playertesttask.presentation.ui.fragments.videolistsfragment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.otterspy.playertesttask.databinding.HolderHorizontalListBinding
import io.otterspy.playertesttask.domain.model.Item
import io.otterspy.playertesttask.presentation.ui.fragments.videolistsfragment.adapters.holders.HorizontalListViewHolder

class HorizontalListAdapter : ListAdapter<Item, RecyclerView.ViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.snippet.channelId == newItem.snippet.channelId
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HorizontalListViewHolder(
            HolderHorizontalListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HorizontalListViewHolder -> {
                getItem(position).let { item ->
                    holder.bind(item)
                }
            }
        }
    }

}