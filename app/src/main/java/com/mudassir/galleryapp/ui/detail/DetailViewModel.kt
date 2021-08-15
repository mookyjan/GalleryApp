package com.mudassir.galleryapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mudassir.galleryapp.ui.list.model.ImageUiModel
import javax.inject.Inject

class DetailViewModel @Inject constructor() : ViewModel() {

    val _imageUiModel = MutableLiveData<ImageUiModel>()
    val imageUiModel : LiveData<ImageUiModel> = _imageUiModel

}