package com.mudassir.galleryapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.github.ajalt.timberkt.Timber
import com.mudassir.domain.entity.ImageItemEntity
import com.mudassir.domain.usecase.GetImageListUseCase
import com.mudassir.galleryapp.ui.base.BaseViewModel
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

class GalleryListViewModel @Inject constructor(private val getImageListUseCase: GetImageListUseCase) :
    BaseViewModel() {

    private val TAG: String = GalleryListViewModel::class.java.name

    private val _imageList = MutableLiveData<PagingData<ImageItemEntity>>()
    var imageList: LiveData<PagingData<ImageItemEntity>> = _imageList


    init {
        getImageList()
    }


    fun getImageList(isRefresh: Boolean = true) {
        _loading.postValue(true)
        val result = getImageListUseCase.execute(isRefresh)
//            .map { it.map { it.mapToPresentation() } }
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

//    fun getImageList(params: GetImageListUseCase.Params) {
//        operationStatus.value = Operation.Started
//        getImageListUseCase(viewModelScope, params) {
//            Timber.d { "response $it" }
//            it.either(::handleFailure)
//
//            {
//
//                Timber.d { "$TAG image list api response $it" }
//                _imageList.value = it
//                operationStatus.value = Operation.Completed
//            }
//        }
//    }
}