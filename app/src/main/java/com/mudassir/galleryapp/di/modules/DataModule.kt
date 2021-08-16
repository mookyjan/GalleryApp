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

    /**
     * provide galleryService
     */

    @Provides
    fun provideImageListRemoteDataSource(galleryAppService: GalleryAppService) =
        GetImagesListRemoteDataSource(galleryAppService)

    /**
     * provide remote data source
     */
    @Provides
    fun provideImagesRepository(getImagesListRemoteDataSource: GetImagesListRemoteDataSource) =
        ImagesListRepository(getImagesListRemoteDataSource)


    /**
     * provide ImageList gateWay
     */
    @Provides
    fun provideImageListGateWay(imagesListRepository: ImagesListRepository): ImageListGateWay =
        ImageListGateWayImpl(imagesListRepository)


}