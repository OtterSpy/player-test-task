package io.otterspy.playertesttask.presentation.ui.fragments.videolistsfragment.adapters.holders

import com.bumptech.glide.Glide
import io.otterspy.playertesttask.databinding.HolderHorizontalListBinding
import io.otterspy.playertesttask.domain.model.Item
import io.otterspy.playertesttask.utils.BaseViewHolder

class HorizontalListViewHolder(binding: HolderHorizontalListBinding) :
    BaseViewHolder<HolderHorizontalListBinding, Item>(binding) {
    override fun bind(item: Item) {
        Glide.with(itemView.context).load(item.snippet.thumbnails.medium.url)
            .into(binding.listHolderImageView)
        binding.channelNameTextView.text = item.snippet.channelTitle
        binding.videoNameTextView.text = item.snippet.title
    }
}