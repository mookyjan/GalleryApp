package com.mudassir.galleryapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mudassir.galleryapp.ui.detail.DetailViewModel
import com.mudassir.galleryapp.ui.list.GalleryListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory


    /**
     * gallery viewModel
     */
    @Binds
    @IntoMap
    @ViewModelKey(GalleryListViewModel::class)
    abstract fun provideGalleyListViewModel(viewModel: GalleryListViewModel): ViewModel

    /**
     * detail viewModel
     */
    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun provideDetailViewModel(viewModel: DetailViewModel): ViewModel

}