package io.otterspy.playertesttask.presentation.ui.fragments.mediaplayerfragment

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.views.YouTubePlayerSeekBarListener
import io.otterspy.playertesttask.R
import io.otterspy.playertesttask.databinding.FragmentMediaPlayerBinding


class MediaPlayerFragment : Fragment() {

    private var _binding: FragmentMediaPlayerBinding? = null
    private val binding get() = _binding!!
    private var currentVideo = 0

    private val args: MediaPlayerFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMediaPlayerBinding.inflate(inflater, container, false)
        binding.closeMediaPlayerImageButton.setOnClickListener {
            requireActivity().onBackPressed()
        }

        initYouTubePlayer()

        return binding.root
    }

    private fun setShadowColor(url: String) {
        Glide.with(requireContext()).asBitmap().load(url)
            .into(object : CustomTarget<Bitmap>() {
                @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    Palette.from(resource).generate { palette ->
                        binding.backgroundShadowView.background.setTint(
                            palette?.vibrantSwatch?.rgb ?: ContextCompat.getColor(
                                requireContext(), R.color.main_text_color
                            )
                        )
                        binding.mediaPlayerShadowView.background.setTint(
                            palette?.vibrantSwatch?.rgb ?: ContextCompat.getColor(
                                requireContext(), R.color.main_text_color
                            )
                        )
                    }
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    /* no-op */
                }

            })
    }

    private fun initYouTubePlayer() {

        binding.youTubePlayer.addYouTubePlayerListener(binding.youTubePlayerSeekbar)
        binding.mediaPlayerVideoNameTextView.text = args.item.snippet.title
        binding.mediaPlayerChannelNameTextView.text = args.item.snippet.channelTitle

        setShadowColor(args.item.snippet.thumbnails.medium.url)

        binding.youTubePlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                binding.youTubePlayerSeekbar.youtubePlayerSeekBarListener = (object :
                    YouTubePlayerSeekBarListener {
                    override fun seekTo(time: Float) {
                        youTubePlayer.seekTo(time)
                    }
                })

                binding.playerPlayPauseImageView.setOnClickListener {
                    it.isActivated = !it.isActivated
                    when (it.isActivated) {
                        true -> {
                            youTubePlayer.pause()
                        }
                        false -> {
                            youTubePlayer.play()
                        }
                    }
                }
                youTubePlayer.loadVideo(args.item.id, 0f)
                binding.playerForwardImageButton.setOnClickListener {
                    if (currentVideo != args.itemIdList.size) {
                        setShadowColor(args.itemIdList[currentVideo + 1].snippet.thumbnails.medium.url)
                        youTubePlayer.loadVideo(args.itemIdList[currentVideo + 1].id, 0f)
                        binding.mediaPlayerVideoNameTextView.text =
                            args.itemIdList[currentVideo + 1].snippet.title
                        binding.mediaPlayerChannelNameTextView.text =
                            args.itemIdList[currentVideo + 1].snippet.channelTitle
                        currentVideo += 1
                    }
                }
                binding.playerBackImageButton.setOnClickListener {
                    if (currentVideo != 0) {
                        setShadowColor(args.itemIdList[currentVideo - 1].snippet.thumbnails.medium.url)
                        youTubePlayer.loadVideo(args.itemIdList[currentVideo - 1].id, 0f)
                        binding.mediaPlayerVideoNameTextView.text =
                            args.itemIdList[currentVideo - 1].snippet.title
                        binding.mediaPlayerChannelNameTextView.text =
                            args.itemIdList[currentVideo - 1].snippet.channelTitle
                        currentVideo -= 1
                    }
                }
            }
        })
    }
}