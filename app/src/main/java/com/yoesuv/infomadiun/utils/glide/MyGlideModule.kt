package com.yoesuv.infomadiun.utils.glide

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.yoesuv.infomadiun.R

@GlideModule
class MyGlideModule: AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
        val req = RequestOptions()
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .format(DecodeFormat.PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        builder.setDefaultRequestOptions(req)
    }
}