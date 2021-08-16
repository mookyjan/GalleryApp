package com.mudassir.galleryapp.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.mudassir.galleryapp.R


fun View.isVisible() = this.visibility == View.VISIBLE

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}


fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)


fun <T : ViewDataBinding> ViewGroup.dataBind(@LayoutRes layoutRes: Int) =
    DataBindingUtil.inflate<T>(LayoutInflater.from(context), layoutRes, this, false)!!

fun <T : ViewDataBinding> ViewGroup.dataBind(@LayoutRes layoutRes: Int, viewType: Int) =
    DataBindingUtil.inflate<T>(LayoutInflater.from(context), layoutRes, this, false)!!





@BindingAdapter("imageUrl")
fun ImageView.loadImage(uri: String?) {

    this.post {
        val myOptions = RequestOptions()
            .override(this.width, this.height)
            .centerCrop()
        Glide
            .with(this.context)
            .load(uri)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.loading)
            .error(R.drawable.ic_gallery)
            .apply(myOptions)
            .into(this)
    }

}


