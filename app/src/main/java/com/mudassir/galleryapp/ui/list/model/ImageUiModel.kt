package com.mudassir.galleryapp.ui.list.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageUiModel(
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val downloadUrl: String
) : Parcelable

fun com.mudassir.domain.entity.ImageItemEntity.mapToPresentation(): ImageUiModel
        = ImageUiModel(id,author,width, height, downloadUrl )