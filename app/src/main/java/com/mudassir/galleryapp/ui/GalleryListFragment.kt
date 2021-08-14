package com.mudassir.galleryapp.ui

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
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ajalt.timberkt.Timber
import com.mudassir.domain.entity.ImageItemEntity
import com.mudassir.galleryapp.R
import com.mudassir.galleryapp.databinding.GalleryListFragmentBinding
import com.mudassir.galleryapp.di.modules.ViewModelFactory
import com.mudassir.galleryapp.extensions.observe
import com.mudassir.galleryapp.ui.base.BaseFragment
import com.mudassir.galleryapp.ui.adapter.GalleryListAdapter
import com.mudassir.galleryapp.ui.adapter.GalleryListAdapterCallback
import com.mudassir.galleryapp.ui.adapter.LoadingGridStateAdapter
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
        setupRecyclerView()
        observeEvents()
    }


    private fun observeEvents() {
        viewModel.imageList.observe(viewLifecycleOwner, Observer {
            Timber.d { "list of images $it" }
            mAdapter.submitData(lifecycle, it)

        })


        viewModel.loading.observe(viewLifecycleOwner){
            showProgress(it,false)
        }
//
//        viewModel.errorEvent.observe(viewLifecycleOwner){
//            Toast.makeText(requireContext(),"$it", Toast.LENGTH_SHORT).show()
//        }

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

    override fun onItemClicked(item: ImageItemEntity) {
        Timber.d { "image clicked $item" }
        Toast.makeText(requireContext(),"Click on ${item.author}",Toast.LENGTH_SHORT).show()
    }
}