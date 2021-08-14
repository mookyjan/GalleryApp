package com.mudassir.galleryapp.di.modules

import com.mudassir.domain.SchedulerProvider
import com.mudassir.domain.gateway.ImageListGateWay
import com.mudassir.domain.usecase.GetImageListUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideImageListUseCase(
        schedulerProvider: SchedulerProvider,
        imageListGateWay: ImageListGateWay
    ) =
        GetImageListUseCase(schedulerProvider, imageListGateWay)
}