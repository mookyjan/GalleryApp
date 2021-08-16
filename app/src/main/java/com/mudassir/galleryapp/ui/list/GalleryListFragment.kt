package com.mudassir.galleryapp.ui.list

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.github.ajalt.timberkt.Timber
import com.mudassir.domain.entity.ImageItemEntity
import com.mudassir.galleryapp.AppConstants
import com.mudassir.galleryapp.R
import com.mudassir.galleryapp.databinding.GalleryListFragmentBinding
import com.mudassir.galleryapp.di.modules.ViewModelFactory
import com.mudassir.galleryapp.ui.base.BaseFragment
import com.mudassir.galleryapp.ui.adapter.GalleryListAdapter
import com.mudassir.galleryapp.ui.adapter.GalleryListAdapterCallback
import com.mudassir.galleryapp.ui.adapter.LoadingGridStateAdapter
import com.mudassir.galleryapp.ui.list.model.ImageUiModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class GalleryListFragment : BaseFragment(), GalleryListAdapterCallback {

    companion object {
        fun newInstance() = GalleryListFragment()
    }

    lateinit var mBinding: GalleryListFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: GalleryListViewModel by viewModels { viewModelFactory }
    @Inject
    lateinit var mAdapter: GalleryListAdapter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.gallery_list_fragment,
            container, false
        )
        mBinding.viewModel = viewModel
        mBinding.lifecycleOwner = this
        progressBar = mBinding.progressBar
        observeEvents()
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setupRecyclerView()
        observeEvents()
    }

    private fun initViews(){
        mBinding.lySwipeRefresh.setProgressViewOffset(false, mBinding.root.height / 2, 500)
    }

    private fun observeEvents() {
        viewModel.imageList.observe(viewLifecycleOwner) {
            Timber.d { "list of images $it" }
            mAdapter.submitData(lifecycle, it)

        }

        viewModel.error.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(),"$it", Toast.LENGTH_SHORT).show()
        }

    }

    private fun setupRecyclerView() {
        mAdapter.setupListener(this)
        mBinding.rvImages.adapter = mAdapter
        mBinding.rvImages.adapter = mAdapter.withLoadStateFooter(
            footer = LoadingGridStateAdapter()
        )
        mAdapter.addLoadStateListener { loadState ->
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error

            errorState?.let {
                AlertDialog.Builder(view?.context)
                    .setTitle(R.string.txt_error)
                    .setMessage(it.error.localizedMessage)
                    .setNegativeButton(R.string.txt_cancel) { dialog, _ ->
                        dialog.dismiss()
                    }
                    .setPositiveButton(R.string.txt_retry) { _, _ ->
                        mAdapter.retry()
                    }
                    .show()
            }
        }
    }

    override fun onItemClicked(item: ImageUiModel) {
        val bundle = Bundle()
        bundle.putParcelable(AppConstants.IMAGE_ARGU, item)
        findNavController().navigate(R.id.action_galleryFragment_to_detailFragment, bundle)
    }
}