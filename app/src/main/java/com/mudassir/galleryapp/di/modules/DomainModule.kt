package com.mudassir.galleryapp.di.modules

import com.mudassir.domain.util.SchedulerProvider
import com.mudassir.domain.gateway.ImageListGateWay
import com.mudassir.domain.usecase.GetImageListUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    /**
     * provide imageList useCase
     */
    @Provides
    fun provideImageListUseCase(
        schedulerProvider: SchedulerProvider,
        imageListGateWay: ImageListGateWay
    ) =
        GetImageListUseCase(schedulerProvider, imageListGateWay)
}