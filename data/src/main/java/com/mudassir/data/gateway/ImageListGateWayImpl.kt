package com.mudassir.data.gateway

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.mudassir.data.paging.ImagesDataSource
import com.mudassir.data.repository.ImagesListRepository
import com.mudassir.domain.entity.ImageItemEntity
import com.mudassir.domain.gateway.ImageListGateWay
import io.reactivex.rxjava3.core.Flowable

class ImageListGateWayImpl (private val imagesListRepository: ImagesListRepository): ImageListGateWay{


    override fun getImageList(refresh: Boolean?): Flowable<PagingData<ImageItemEntity>> {
        val pager =  Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = true,
                maxSize = 30,
                prefetchDistance = 5,
                initialLoadSize = 20)
        ){
            ImagesDataSource(imagesListRepository)
        }.flowable

        return pager
    }
}