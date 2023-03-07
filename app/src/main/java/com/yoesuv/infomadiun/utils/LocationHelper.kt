package com.yoesuv.infomadiun.utils

import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.yoesuv.infomadiun.data.DEFAULT_LATITUDE
import com.yoesuv.infomadiun.data.DEFAULT_LONGITUDE
import com.yoesuv.infomadiun.menu.maps.models.MarkerTag

fun setDefaultLocation(googleMap: GoogleMap?) {
    googleMap?.apply {
        this.clear()
        moveCamera(CameraUpdateFactory.newLatLng(LatLng(DEFAULT_LATITUDE, DEFAULT_LONGITUDE)))
        animateCamera(CameraUpdateFactory.zoomTo(9F))
    }
}

fun setupMarkerAnimation(googleMap: GoogleMap?) {
    googleMap?.setOnMarkerClickListener {
        val tag: MarkerTag = it.tag as MarkerTag
        if (tag.type == 0) {
            val start = SystemClock.uptimeMillis()
            val duration = 1200L

            val handler = Handler(Looper.getMainLooper())
            val anim = BounceAnimation(start, duration, it, handler)
            handler.post(anim)
        }
        if (tag.type == 3) {
            it.hideInfoWindow()
        } else {
            it.showInfoWindow()
        }
        return@setOnMarkerClickListener true
    }
}