package com.mudassir.galleryapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.mudassir.galleryapp.R
import com.mudassir.galleryapp.databinding.SingleItemGalleryBinding
import com.mudassir.galleryapp.ui.base.BaseViewHolder
import com.mudassir.galleryapp.ui.list.model.ImageUiModel

class GalleryListAdapter :
    PagingDataAdapter<ImageUiModel, GalleryListAdapter.GalleryItemViewHolder>(COMPARATOR) {

    var callback: GalleryListAdapterCallback? = null

    fun setupListener(listener: GalleryListAdapterCallback) {
        this.callback = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val mBinding: SingleItemGalleryBinding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.single_item_gallery,
                parent,
                false
            )
        return GalleryItemViewHolder(
            mBinding
        )
    }

    override fun onBindViewHolder(holder: GalleryItemViewHolder, position: Int) {

        getItem(position)?.let {
            holder.bind(it, position)
        }
    }

    inner class GalleryItemViewHolder(val binding: SingleItemGalleryBinding) :
        BaseViewHolder<ImageUiModel>(binding.root) {
        override fun bind(item: ImageUiModel, position: Int) {

            binding.apply {
                model = item
                binding.root.setOnClickListener {
                    callback?.onItemClicked(item)
                }
                executePendingBindings()
            }
        }
    }


    object COMPARATOR : DiffUtil.ItemCallback<ImageUiModel>() {
        override fun areItemsTheSame(oldItem: ImageUiModel, newItem: ImageUiModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ImageUiModel,
            newItem: ImageUiModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}

interface GalleryListAdapterCallback {
    fun onItemClicked(item: ImageUiModel)
}

