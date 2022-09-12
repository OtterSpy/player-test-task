package io.otterspy.playertesttask.presentation.ui.fragments.videolistsfragment.adapters.holders

import com.bumptech.glide.Glide
import io.otterspy.playertesttask.databinding.HolderSearchListBinding
import io.otterspy.playertesttask.domain.model.ItemSearch
import io.otterspy.playertesttask.utils.BaseViewHolder

class SearchListViewHolder(binding: HolderSearchListBinding) :
    BaseViewHolder<HolderSearchListBinding, ItemSearch>(binding) {
    override fun bind(item: ItemSearch) {
        Glide.with(itemView.context).load(item.snippet.thumbnails.medium.url)
            .into(binding.searchImageView)
        binding.searchVideoNameTextView.text = item.snippet.title
        binding.searchChannelNameTextView.text = item.snippet.channelTitle
    }
}