package com.mudassir.data.remote

import com.mudassir.data.remote.api.GalleryAppService
import com.mudassir.data.remote.model.ImageListResponse
import io.reactivex.rxjava3.core.Single

class GetImagesListRemoteDataSource(private val galleryAppService: GalleryAppService) {

    fun getImagesList(page: Int): Single<List<ImageListResponse>> =
        galleryAppService.getImageList(page = page)

}