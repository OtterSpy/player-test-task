package io.otterspy.playertesttask.presentation.ui.fragments.videolistsfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.otterspy.playertesttask.common.Constants.API_KEY
import io.otterspy.playertesttask.common.Constants.CHART
import io.otterspy.playertesttask.common.Constants.PART
import io.otterspy.playertesttask.common.Constants.REGION_CODE
import io.otterspy.playertesttask.common.Resource
import io.otterspy.playertesttask.domain.model.Item
import io.otterspy.playertesttask.domain.usecase.GetVideosUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class VideoListsViewModel @Inject constructor(
    private val getMostPopularMusicVideosUseCase: GetVideosUseCase
) : ViewModel() {

    private val _mostPopularMusicHorizontalList = MutableLiveData<Resource<List<Item>>>()
    val mostPopularMusicHorizontalList: LiveData<Resource<List<Item>>>
        get() = _mostPopularMusicHorizontalList

    private val _mostPopularMusicGridList = MutableLiveData<Resource<List<Item>>>()
    val mostPopularMusicGridList: LiveData<Resource<List<Item>>>
        get() = _mostPopularMusicGridList

    fun getMostPopularMusicHorizontalList(category: Int, maxResults: Int) {
        getMostPopularList(category, maxResults, _mostPopularMusicHorizontalList)
    }

    fun getMostPopularGridList(category: Int?, maxResults: Int) {
        getMostPopularList(category, maxResults, _mostPopularMusicGridList)
    }

    private fun getMostPopularList(category: Int?, maxResults: Int, musicList: MutableLiveData<Resource<List<Item>>>) {
        musicList.value = Resource.loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                musicList.postValue(
                    Resource.success(
                        getMostPopularMusicVideosUseCase(
                            API_KEY,
                            PART,
                            CHART,
                            REGION_CODE,
                            maxResults,
                            category
                        )
                    )
                )
            } catch (t: Throwable) {
                musicList.postValue(
                    Resource.error(t.message ?: "Oops. Something Went Wrong")
                )
            }
        }
    }

}