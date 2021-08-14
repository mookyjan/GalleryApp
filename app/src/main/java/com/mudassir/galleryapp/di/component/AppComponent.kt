package com.mudassir.galleryapp.di.component

import android.app.Application
import android.content.Context
import com.mudassir.data.NetworkModule
import com.mudassir.galleryapp.GalleryApp
import com.mudassir.galleryapp.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        AppModule::class,
        AndroidInjectionModule::class,
        ViewModelFactoryModule::class,
//        ActivityModule::class,
        FragmentModule::class,
        NetworkModule::class,
        DomainModule::class,
        DataModule::class
    )
)
interface AppComponent {

    fun inject(application: GalleryApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}