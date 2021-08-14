package com.mudassir.galleryapp.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.aminography.redirectglide.GlideApp
import com.aminography.redirectglide.GlideApp.with
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.mudassir.galleryapp.R
import com.squareup.picasso.Picasso


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



fun setImageUrl(view: ImageView,url: String?) {
    Glide.with(view.context)
        .asDrawable()
        .load(url).placeholder(R.drawable.ic_gallery).dontAnimate().into(view)
}


@BindingAdapter("imageUrl")
fun ImageView.loadImage(uri: String?) {

    this.post {

        val myOptions = RequestOptions()
            .override(this.width, this.height)
            .centerCrop()

//        GlideApp.with(this.context)
//            .load(uri)
//            .into(this)

        Glide
            .with(this.context)
            .load("https://i.picsum.photos/id/0/5616/3744.jpg?hmac=3GAAioiQziMGEtLbfrdbcoenXoWAW-zlyEAMkfEdBzQ")
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.loading)
            .error(R.drawable.ic_gallery)
            .apply(myOptions)
            .into(this)

//        Picasso.get().load(uri).resize(250, 250).centerCrop().into(this);

    }

}


