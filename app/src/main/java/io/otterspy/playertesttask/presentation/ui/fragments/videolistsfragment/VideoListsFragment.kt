package io.otterspy.playertesttask.presentation.ui.fragments.videolistsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import io.otterspy.playertesttask.R
import io.otterspy.playertesttask.common.Constants.MUSIC_CATEGORY
import io.otterspy.playertesttask.common.Resource
import io.otterspy.playertesttask.databinding.FragmentVideoListsBinding
import io.otterspy.playertesttask.presentation.ui.MainApplication.Companion.getAppComponent
import io.otterspy.playertesttask.presentation.ui.fragments.videolistsfragment.adapters.HorizontalListAdapter

class VideoListsFragment : Fragment() {

    private var _binding: FragmentVideoListsBinding? = null
    private val binding get() = _binding!!

    private val horizontalListAdapter by lazy { HorizontalListAdapter() }

    private val viewModel: VideoListsViewModel by viewModels {
        requireActivity().getAppComponent().factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentVideoListsBinding.inflate(inflater, container, false)

        initObserver()
        getMusicList()
        setDataToView()

        return binding.root
    }

    private fun initObserver() {
        viewModel.mostPopularMusicList.observe(viewLifecycleOwner) { musicList ->
            when (musicList) {
                is Resource.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        "Cant Load Horizontal List",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Resource.Loading -> {}
                is Resource.Success -> {
                    horizontalListAdapter.submitList(musicList.data)
                }
            }
        }
    }

    private fun getMusicList() {
        viewModel.getMostPopularMusicList(MUSIC_CATEGORY)
    }

    private fun setDataToView() {
        binding.horizontalListTitleTextView.text = requireActivity().resources.getText(R.string.horizontal_list_title)
        binding.horizontalListRecyclerView.adapter = horizontalListAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}