package io.otterspy.playertesttask.presentation.ui.fragments.videolistsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import io.otterspy.playertesttask.R
import io.otterspy.playertesttask.common.Constants.MUSIC_CATEGORY
import io.otterspy.playertesttask.common.Resource
import io.otterspy.playertesttask.common.Util.getDp
import io.otterspy.playertesttask.databinding.FragmentVideoListsBinding
import io.otterspy.playertesttask.domain.model.Item
import io.otterspy.playertesttask.presentation.helpers.GridSpacingItemDecoration
import io.otterspy.playertesttask.presentation.ui.MainApplication.Companion.getAppComponent
import io.otterspy.playertesttask.presentation.ui.fragments.videolistsfragment.adapters.GridListAdapter
import io.otterspy.playertesttask.presentation.ui.fragments.videolistsfragment.adapters.HorizontalListAdapter

class VideoListsFragment : Fragment() {

    private var _binding: FragmentVideoListsBinding? = null
    private val binding get() = _binding!!

    private val horizontalListAdapter by lazy { HorizontalListAdapter() }
    private val gridListAdapter by lazy { GridListAdapter() }

    private val viewModel: VideoListsViewModel by viewModels {
        requireActivity().getAppComponent().factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentVideoListsBinding.inflate(inflater, container, false)

        initObserver()
        getDataList()
        setDataToView()

        return binding.root
    }

    private fun initObserver() {
        viewModel.mostPopularMusicHorizontalList.observe(viewLifecycleOwner) { musicList ->
            getVideoList(horizontalListAdapter, musicList)
        }
        viewModel.mostPopularMusicGridList.observe(viewLifecycleOwner) { mostPopularList ->
            getVideoList(gridListAdapter, mostPopularList)
        }
    }

    private fun getVideoList(
        adapter: ListAdapter<Item, RecyclerView.ViewHolder>,
        videoList: Resource<List<Item>>
    ) {
        when (videoList) {
            is Resource.Failure -> {
                Toast.makeText(
                    requireContext(),
                    "Cant Load Horizontal List",
                    Toast.LENGTH_SHORT
                ).show()
            }
            is Resource.Loading -> {}
            is Resource.Success -> {
                adapter.submitList(videoList.data)
            }
        }
    }

    private fun getDataList() {
        viewModel.getMostPopularMusicHorizontalList(MUSIC_CATEGORY, 20)
        viewModel.getMostPopularGridList(null, 30)
    }

    private fun setDataToView() {

        horizontalListAdapter.setOnItemClickListener { item ->
            setDataToMiniPlayer(
                item,
                binding.miniPlayerPreviewImageView,
                binding.miniPlayerVideoNameTextView,
                binding.miniPlayerChannelNameTextView
            )
        }

        gridListAdapter.setOnItemClickListener { item ->
            setDataToMiniPlayer(
                item,
                binding.miniPlayerPreviewImageView,
                binding.miniPlayerVideoNameTextView,
                binding.miniPlayerChannelNameTextView
            )
        }

        binding.horizontalListTitleTextView.text =
            requireActivity().resources.getText(R.string.horizontal_list_title)
        binding.horizontalListRecyclerView.adapter = horizontalListAdapter
        binding.gridListTitleTextView.text =
            requireActivity().resources.getText(R.string.grid_list_title)
        binding.gridListRecyclerView.addItemDecoration(
            GridSpacingItemDecoration(
                3,
                requireActivity().getDp(14f),
                false
            )
        )
        binding.gridListRecyclerView.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.gridListRecyclerView.adapter = gridListAdapter
    }

    private fun setDataToMiniPlayer(
        item: Item,
        previewImage: ImageView,
        videoName: TextView,
        channelName: TextView
    ) {
        Glide.with(requireContext()).load(item.snippet.thumbnails.medium.url).into(previewImage)
        videoName.text = item.snippet.title
        channelName.text = item.snippet.channelTitle
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}