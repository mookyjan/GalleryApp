package com.mudassir.data.repository

import com.mudassir.data.remote.GetImagesListRemoteDataSource
import com.mudassir.data.remote.model.ImageListResponse
import io.reactivex.rxjava3.core.Single

class ImagesListRepository(private val getImagesListRemoteDataSource: GetImagesListRemoteDataSource) {

    fun getImagesList(refresh: Boolean? = false, page: Int): Single<List<ImageListResponse>> {
        val remoteData = getImagesListRemoteDataSource.getImagesList(page)
        return remoteData
    }
}