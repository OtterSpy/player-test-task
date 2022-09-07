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
import io.otterspy.playertesttask.domain.usecase.GetMostPopularMusicVideosUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class VideoListsViewModel @Inject constructor(
    private val getMostPopularMusicVideosUseCase: GetMostPopularMusicVideosUseCase
) : ViewModel() {

    private val _mostPopularMusicList = MutableLiveData<Resource<List<Item>>>()
    val mostPopularMusicList: LiveData<Resource<List<Item>>>
        get() = _mostPopularMusicList

    fun getMostPopularMusicList(category: Int) {
        _mostPopularMusicList.value = Resource.loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _mostPopularMusicList.postValue(
                    Resource.success(
                        getMostPopularMusicVideosUseCase(
                            API_KEY,
                            PART,
                            CHART,
                            REGION_CODE,
                            20,
                            category
                        )
                    )
                )
            } catch (t: Throwable) {
                _mostPopularMusicList.postValue(
                    Resource.error(t.message ?: "Oops. Something Went Wrong")
                )
            }
        }
    }

}