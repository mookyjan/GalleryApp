package com.mudassir.galleryapp.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.mudassir.galleryapp.AppConstants
import com.mudassir.galleryapp.R
import com.mudassir.galleryapp.databinding.DetailFragmentBinding
import com.mudassir.galleryapp.di.modules.ViewModelFactory
import com.mudassir.galleryapp.ui.base.BaseFragment
import com.mudassir.galleryapp.ui.list.model.ImageUiModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class DetailFragment : BaseFragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    lateinit var mBinding: DetailFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: DetailViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel._imageUiModel.value = arguments?.getParcelable<ImageUiModel>(AppConstants.IMAGE_ARGU)!!
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.detail_fragment, container, false
        )
        mBinding.viewModel = viewModel
        mBinding.lifecycleOwner = this
        return mBinding.root
    }


}