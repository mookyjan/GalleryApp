package com.mudassir.galleryapp.di.modules

import com.mudassir.galleryapp.ui.detail.DetailFragment
import com.mudassir.galleryapp.ui.list.GalleryListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    /**
     * gallery Fragment
     */
    @ContributesAndroidInjector
    abstract fun galleryListFragment(): GalleryListFragment

    /**
     * detail fragment
     */
    @ContributesAndroidInjector
    abstract fun detailListFragment(): DetailFragment

}