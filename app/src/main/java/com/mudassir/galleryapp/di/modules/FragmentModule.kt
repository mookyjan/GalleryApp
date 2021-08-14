package com.mudassir.galleryapp.di.modules

import com.mudassir.galleryapp.ui.GalleryListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun imageListFragment(): GalleryListFragment

}