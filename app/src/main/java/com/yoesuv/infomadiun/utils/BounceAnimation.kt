package com.yoesuv.infomadiun.utils

import android.os.Handler
import android.os.SystemClock
import android.view.animation.BounceInterpolator
import android.view.animation.Interpolator

import com.google.android.gms.maps.model.Marker

class BounceAnimation(private val mStart: Long, private val mDuration: Long, private val mMarker: Marker, private val mHandler: Handler) : Runnable {
    private val mInterpolator: Interpolator

    init {
        mInterpolator = BounceInterpolator()
    }

    override fun run() {
        val elapsed = SystemClock.uptimeMillis() - mStart
        val t = Math.max(1 - mInterpolator.getInterpolation(elapsed.toFloat() / mDuration), 0f)
        mMarker.setAnchor(0.5f, 1.0f + 1.2f * t)

        if (t > 0.0) {
            // Post again 16ms later.
            mHandler.postDelayed(this, 16L)
        }
    }
}
