package com.yoesuv.infomadiun.utils.binding

import android.animation.Animator
import androidx.databinding.BindingAdapter
import androidx.appcompat.widget.AppCompatImageView
import android.view.View
import com.bumptech.glide.GenericTransitionOptions
import com.yoesuv.infomadiun.data.Constants
import com.yoesuv.infomadiun.utils.glide.GlideApp

class Converters {

    companion object {

        @JvmStatic
        @BindingAdapter("hideView")
        fun hideView(view: View, status: Boolean){
            if (!status) {
                view.animate().alpha(0F).setDuration(Constants.ANIMATION_TIME).setListener(object : Animator.AnimatorListener{
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
                view.animate().alpha(1F).duration = Constants.ANIMATION_TIME
            }
        }

    }

}