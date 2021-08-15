package com.mudassir.galleryapp.di.modules

import android.app.Application
import android.content.Context
import com.mudassir.domain.SchedulerProvider
import com.mudassir.galleryapp.di.modules.scheduler.AppSchedulers
import com.mudassir.galleryapp.ui.adapter.GalleryListAdapter
import com.mudassir.galleryapp.util.IResourceProvider
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    /**
     * providing context
     */
    @Provides
    @Singleton
    @Named("application.Context")
    fun provideContext(application: Application) : Context {
        return application.applicationContext
    }

    /**
     * providing Scheduler
     */
    @Provides
    @Singleton
    fun provideSchedulers() : SchedulerProvider = AppSchedulers()

    /**
     * providing ResourceProvider
     */
    @Provides
    @Singleton
    fun provideResource(context: Context) = IResourceProvider(context)

    /**
     * providing GalleryAdapter
     */
    @Provides
    @Singleton
    fun provideGalleryAdapter() = GalleryListAdapter()
}