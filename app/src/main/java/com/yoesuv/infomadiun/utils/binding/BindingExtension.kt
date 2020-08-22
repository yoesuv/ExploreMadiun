package com.yoesuv.infomadiun.utils.binding

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.GenericTransitionOptions
import com.yoesuv.infomadiun.utils.glide.GlideApp

@BindingAdapter("loadImage")
fun AppCompatImageView.loadImage(imageUrl: String) {
    GlideApp.with(this.context.applicationContext)
        .load(imageUrl)
        .transition(GenericTransitionOptions.with(android.R.anim.fade_in))
        .into(this)
}