package io.otterspy.playertesttask.presentation.ui.fragments.videolistsfragment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.otterspy.playertesttask.databinding.HolderSearchListBinding
import io.otterspy.playertesttask.domain.model.ItemSearch
import io.otterspy.playertesttask.presentation.ui.fragments.videolistsfragment.adapters.holders.SearchListViewHolder

class SearchListAdapter : ListAdapter<ItemSearch, RecyclerView.ViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<ItemSearch>() {
        override fun areItemsTheSame(oldItem: ItemSearch, newItem: ItemSearch): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ItemSearch, newItem: ItemSearch): Boolean {
            return oldItem.snippet.channelId == newItem.snippet.channelId
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SearchListViewHolder(
            HolderSearchListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SearchListViewHolder -> {
                getItem(position).let { item ->
                    holder.bind(item)
                }
            }
        }
    }
}