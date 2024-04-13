package com.yoesuv.infomadiun.utils.binding

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.yoesuv.infomadiun.R

@BindingAdapter("loadImage")
fun AppCompatImageView.loadImage(imageUrl: String) {
    Glide.with(this.context.applicationContext)
        .load(imageUrl)
        .placeholder(R.drawable.placeholder_image)
        .error(R.drawable.placeholder_image)
        .format(DecodeFormat.PREFER_ARGB_8888)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .transition(GenericTransitionOptions.with(android.R.anim.fade_in))
        .into(this)
}