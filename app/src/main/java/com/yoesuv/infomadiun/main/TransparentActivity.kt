package com.yoesuv.infomadiun.main

import android.graphics.Point
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import com.yoesuv.infomadiun.R
import kotlinx.android.synthetic.main.activity_transparent.*

class TransparentActivity: AppCompatActivity() {

    companion object {
        const val EXTRA_DATA_IMAGE = "extra_data_image"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transparent)

        val display = this.windowManager?.defaultDisplay
        val point = Point()
        display?.getSize(point)
        window.setLayout(point.x, point.y)

        Glide.with(this)
                .load(intent.getStringExtra(EXTRA_DATA_IMAGE))
                .apply(RequestOptions().format(DecodeFormat.PREFER_ARGB_8888))
                .transition(GenericTransitionOptions.with(android.R.anim.fade_in))
                .into(photoViewActivity)
    }

}