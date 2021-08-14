package com.mudassir.galleryapp.di.modules

import com.mudassir.data.gateway.ImageListGateWayImpl
import com.mudassir.data.remote.GetImagesListRemoteDataSource
import com.mudassir.data.remote.api.GalleryAppService
import com.mudassir.data.repository.ImagesListRepository
import com.mudassir.domain.gateway.ImageListGateWay
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideImageListRemoteDataSource(galleryAppService: GalleryAppService)
            = GetImagesListRemoteDataSource(galleryAppService)


    @Provides
    fun provideImagesRepository(getImagesListRemoteDataSource: GetImagesListRemoteDataSource)
            = ImagesListRepository(getImagesListRemoteDataSource)

    @Provides
    fun provideMovieGateWay(imagesListRepository: ImagesListRepository): ImageListGateWay
            = ImageListGateWayImpl(imagesListRepository)



}