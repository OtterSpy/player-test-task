package io.otterspy.playertesttask.presentation.ui.fragments.videolistsfragment.adapters.holders

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import io.otterspy.playertesttask.R
import io.otterspy.playertesttask.databinding.HolderGridListBinding
import io.otterspy.playertesttask.domain.model.Item
import io.otterspy.playertesttask.utils.BaseViewHolder

class GridListViewHolder(binding: HolderGridListBinding) :
    BaseViewHolder<HolderGridListBinding, Item>(binding) {
    override fun bind(item: Item) {
        Glide.with(itemView.context).asBitmap().load(item.snippet.thumbnails.medium.url)
            .into(object : CustomTarget<Bitmap>() {
                @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    Palette.from(resource).generate { palette ->
                        binding.gridPreviewImageView.setImageBitmap(resource)
                        binding.shadowBackgroundView.background.setTint(
                            palette?.vibrantSwatch?.rgb ?: ContextCompat.getColor(
                                itemView.context, R.color.main_text_color
                            )
                        )
                    }
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    /* no-op */
                }
            })
        binding.gridListChannelNameTextView.text = item.snippet.channelTitle
        binding.gridListVideoNameTextView.text = item.snippet.title
    }
}