package com.yoesuv.infomadiun.utils.binding

import android.animation.Animator
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.GenericTransitionOptions
import com.yoesuv.infomadiun.data.ANIMATION_TIME
import com.yoesuv.infomadiun.utils.glide.GlideApp

@BindingAdapter("loadImage")
fun AppCompatImageView.loadImage(imageUrl: String) {
    GlideApp.with(this.context.applicationContext)
        .load(imageUrl)
        .transition(GenericTransitionOptions.with(android.R.anim.fade_in))
        .into(this)
}

@BindingAdapter("hideView")
fun View.hideView(status: Boolean) {
    if (!status) {
        this.animate().alpha(0F).setDuration(ANIMATION_TIME).setListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(p0: Animator?) {

            }

            override fun onAnimationEnd(p0: Animator?) {
                this@hideView.visibility = View.GONE
            }

            override fun onAnimationCancel(p0: Animator?) {

            }

            override fun onAnimationStart(p0: Animator?) {

            }

        })
    } else {
        this.visibility = View.VISIBLE
    }
}

@BindingAdapter("showView")
fun  View.showView(status: Boolean) {
    if (status) {
        this.alpha = 0F
    } else {
        this.animate().alpha(1F).duration = ANIMATION_TIME
    }
}