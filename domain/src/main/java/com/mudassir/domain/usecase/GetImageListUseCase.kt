package com.mudassir.domain.usecase

import com.mudassir.domain.FlowableUseCase
import com.mudassir.domain.util.SchedulerProvider
import com.mudassir.domain.entity.ImageItemEntity
import com.mudassir.domain.gateway.ImageListGateWay
import io.reactivex.rxjava3.core.Flowable
import androidx.paging.PagingData

class GetImageListUseCase(
    schedulers: SchedulerProvider,
    private val imageListGateWay: ImageListGateWay
) : FlowableUseCase<Boolean, PagingData<ImageItemEntity>>(schedulers) {

    override fun buildUseCaseObservable(refresh: Boolean): Flowable<PagingData<ImageItemEntity>> {

        return imageListGateWay.getImageList(refresh)
    }
}