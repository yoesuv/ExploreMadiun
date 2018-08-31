package com.yoesuv.infomadiun.utils.binding

import android.animation.Animator
import android.databinding.BindingAdapter
import android.support.v7.widget.AppCompatImageView
import android.view.View
import com.bumptech.glide.GenericTransitionOptions
import com.yoesuv.infomadiun.utils.glide.GlideApp

class Converters {

    companion object {

        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(imageView: AppCompatImageView, imageUrl: String){
            GlideApp.with(imageView.context.applicationContext)
                    .load(imageUrl)
                    .transition(GenericTransitionOptions.with(android.R.anim.fade_in))
                    .into(imageView)
        }

        @JvmStatic
        @BindingAdapter("hideView")
        fun hideView(view: View, status: Boolean){
            if (!status) {
                view.animate().alpha(0F).setDuration(300L).setListener(object : Animator.AnimatorListener{
                    override fun onAnimationRepeat(p0: Animator?) {

                    }

                    override fun onAnimationEnd(p0: Animator?) {
                        view.visibility = View.GONE
                    }

                    override fun onAnimationCancel(p0: Animator?) {

                    }

                    override fun onAnimationStart(p0: Animator?) {

                    }

                })
            } else {
                view.visibility = View.VISIBLE
            }
        }

        @JvmStatic
        @BindingAdapter("showView")
        fun showView(view: View, status: Boolean){
            if (status) {
                view.alpha = 0F
            } else {
                view.animate().alpha(1F).duration = 300L
            }
        }

    }

}