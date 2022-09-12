package io.otterspy.playertesttask.presentation.ui.fragments.videolistsfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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
import io.otterspy.playertesttask.domain.model.ItemIdList
import io.otterspy.playertesttask.domain.model.ItemSearch
import io.otterspy.playertesttask.presentation.helpers.GridSpacingItemDecoration
import io.otterspy.playertesttask.presentation.ui.MainApplication.Companion.getAppComponent
import io.otterspy.playertesttask.presentation.ui.fragments.videolistsfragment.adapters.GridListAdapter
import io.otterspy.playertesttask.presentation.ui.fragments.videolistsfragment.adapters.HorizontalListAdapter
import io.otterspy.playertesttask.presentation.ui.fragments.videolistsfragment.adapters.SearchListAdapter

class VideoListsFragment : Fragment() {

    private var _binding: FragmentVideoListsBinding? = null
    private val binding get() = _binding!!

    private val itemIdList = ItemIdList()

    private val horizontalListAdapter by lazy { HorizontalListAdapter() }
    private val gridListAdapter by lazy { GridListAdapter() }
    private val searchListAdapter by lazy { SearchListAdapter() }

    private val viewModel: VideoListsViewModel by viewModels {
        requireActivity().getAppComponent().factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        itemIdList.clear()

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

    private fun getDataList() {
        viewModel.getMostPopularMusicHorizontalList(MUSIC_CATEGORY, 20)
        viewModel.getMostPopularGridList(null, 30)
    }

    private fun setDataToView() {
        setDataToHorizontalList()
        setDataToGridList()
        setDataToSearchList()
    }

    private fun setDataToHorizontalList() {
        horizontalListAdapter.setOnItemClickListener { item ->
            setDataToMiniPlayer(
                item,
                binding.miniPlayerPreviewImageView,
                binding.miniPlayerVideoNameTextView,
                binding.miniPlayerChannelNameTextView
            )
        }

        binding.horizontalListTitleTextView.text =
            requireActivity().resources.getText(R.string.horizontal_list_title)

        binding.horizontalListRecyclerView.addItemDecoration(
            GridSpacingItemDecoration(
                1,
                requireActivity().getDp(0f),
                false
            )
        )

        binding.horizontalListRecyclerView.adapter = horizontalListAdapter
    }

    private fun setDataToGridList() {
        gridListAdapter.setOnItemClickListener { item ->
            setDataToMiniPlayer(
                item,
                binding.miniPlayerPreviewImageView,
                binding.miniPlayerVideoNameTextView,
                binding.miniPlayerChannelNameTextView
            )
        }

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

    private fun setDataToSearchList() {
        binding.actionBarSearchEditText.doAfterTextChanged {
            binding.searchRecyclerView.isVisible =
                binding.actionBarSearchEditText.text.toString() != ""
        }

        with(binding.actionBarSearchEditText) {
            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchVideo(binding.actionBarSearchEditText.text.toString())
                    viewModel.searchList.observe(viewLifecycleOwner) { searchList ->
                        getSearchList(searchListAdapter, searchList)
                    }
                }
                false
            }
        }
        binding.searchRecyclerView.adapter = searchListAdapter
    }

    private fun getVideoList(
        adapter: ListAdapter<Item, RecyclerView.ViewHolder>,
        videoList: Resource<List<Item>>
    ) {
        when (videoList) {
            is Resource.Failure -> {
                Toast.makeText(
                    requireContext(),
                    "Error. List not loading",
                    Toast.LENGTH_SHORT
                ).show()
            }
            is Resource.Loading -> {}
            is Resource.Success -> {

                for (i: Int in 0 until videoList.data.size) {
                    itemIdList.add(videoList.data[i])
                }
                Log.d("QQQ", "getVideoList: $itemIdList \n ${itemIdList.size}")
                adapter.submitList(videoList.data)
            }
        }
    }

    private fun getSearchList(
        adapter: ListAdapter<ItemSearch, RecyclerView.ViewHolder>,
        searchList: Resource<List<ItemSearch>>
    ) {
        when (searchList) {
            is Resource.Failure -> {
                Toast.makeText(
                    requireContext(),
                    "Error. List not loading",
                    Toast.LENGTH_SHORT
                ).show()
            }
            is Resource.Loading -> {}
            is Resource.Success -> {
                adapter.submitList(searchList.data)
            }
        }
    }

    private fun searchVideo(text: String?) {
        viewModel.getSearchList(20, text)
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

        binding.miniPlayerNamesContainerLinearLayout.setOnClickListener {
            findNavController().navigate(
                VideoListsFragmentDirections.actionVideoListsFragmentToMediaPlayerFragment(
                    item,
                    itemIdList
                )
            )
        }
        binding.miniPlayerPreviewImageView.setOnClickListener {
            findNavController().navigate(
                VideoListsFragmentDirections.actionVideoListsFragmentToMediaPlayerFragment(
                    item,
                    itemIdList
                )
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}