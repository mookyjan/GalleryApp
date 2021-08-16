package com.mudassir.galleryapp.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import androidx.paging.rxjava3.cachedIn
import com.github.ajalt.timberkt.Timber
import com.mudassir.domain.usecase.GetImageListUseCase
import com.mudassir.galleryapp.ui.base.BaseViewModel
import com.mudassir.galleryapp.ui.list.model.ImageUiModel
import com.mudassir.galleryapp.ui.list.model.mapToPresentation
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

class GalleryListViewModel @Inject constructor(private val getImageListUseCase: GetImageListUseCase) :
    BaseViewModel() {

    private val TAG: String = GalleryListViewModel::class.java.name

    private val _imageList = MutableLiveData<PagingData<ImageUiModel>>()
    var imageList: LiveData<PagingData<ImageUiModel>> = _imageList


    init {
        getImageList()
    }

    /**
     * call api to get the list of images
     */
    fun getImageList(isRefresh: Boolean = true) {
        _loading.postValue(true)
        val result = getImageListUseCase.execute(isRefresh)
            .map { it.map { it.mapToPresentation() } }
            .cachedIn(viewModelScope)
        result.subscribeBy(onNext = {
            _loading.postValue(false)
            _imageList.postValue(it)
            Timber.d { "image list api response $it" }
        }, onError = { e ->
            _loading.postValue(false)
            _error.postValue(e.localizedMessage ?: e.message ?: "Unknown error")
            Timber.e { "error on image list api ${e.printStackTrace()}" }
        }, onComplete = {
            _loading.postValue(false)
        }).addTo(compositeDisposable)
    }

    /**
     * refresh the list
     */
    fun refresh() = getImageList(true)

}