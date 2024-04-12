package com.yoesuv.infomadiun.utils.binding

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide

@BindingAdapter("loadImage")
fun AppCompatImageView.loadImage(imageUrl: String) {
    Glide.with(this.context.applicationContext)
        .load(imageUrl)
        .transition(GenericTransitionOptions.with(android.R.anim.fade_in))
        .into(this)
}