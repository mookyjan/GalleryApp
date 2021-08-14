package com.mudassir.domain.gateway

import androidx.paging.PagingData
import com.mudassir.domain.entity.ImageItemEntity
import io.reactivex.rxjava3.core.Flowable

interface ImageListGateWay {

    fun getImageList(refresh: Boolean? = false): Flowable<PagingData<ImageItemEntity>>
}