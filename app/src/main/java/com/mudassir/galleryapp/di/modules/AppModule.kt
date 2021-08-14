package com.mudassir.galleryapp.di.modules

import android.app.Application
import android.content.Context
import com.mudassir.domain.SchedulerProvider
import com.mudassir.galleryapp.di.modules.scheduler.AppSchedulers
import com.mudassir.galleryapp.ui.adapter.GalleryListAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    @Named("application.Context")
    fun provideContext(application: Application) : Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideSchedulers() : SchedulerProvider = AppSchedulers()

//    @Provides
//    @Singleton
//    fun provideResource(context: Context) = IResourceProvider(context)

    @Provides
    @Singleton
    fun provideGalleryAdapter() = GalleryListAdapter()
}