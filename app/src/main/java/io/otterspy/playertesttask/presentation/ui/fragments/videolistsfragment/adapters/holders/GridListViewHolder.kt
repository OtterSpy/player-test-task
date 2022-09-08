package io.otterspy.playertesttask.presentation.ui.fragments.videolistsfragment.adapters.holders

import com.bumptech.glide.Glide
import io.otterspy.playertesttask.databinding.HolderGridListBinding
import io.otterspy.playertesttask.domain.model.Item
import io.otterspy.playertesttask.utils.BaseViewHolder

class GridListViewHolder(binding: HolderGridListBinding) :
    BaseViewHolder<HolderGridListBinding, Item>(binding) {
    override fun bind(item: Item) {
        Glide.with(itemView.context).load(item.snippet.thumbnails.medium.url).into(binding.gridPreviewImageView)
        binding.gridListChannelNameTextView.text = item.snippet.channelTitle
        binding.gridListVideoNameTextView.text = item.snippet.title
    }
}