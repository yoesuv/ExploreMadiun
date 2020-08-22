package com.yoesuv.infomadiun.utils

import android.Manifest
import android.content.Context
import android.os.Handler
import android.os.SystemClock
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.data.DEFAULT_LATITUDE
import com.yoesuv.infomadiun.data.DEFAULT_LONGITUDE
import com.yoesuv.infomadiun.menu.maps.adapters.MyCustomInfoWindowAdapter
import com.yoesuv.infomadiun.menu.maps.models.MarkerTag

fun setDefaultLocation(googleMap: GoogleMap?) {
    googleMap?.apply {
        this.clear()
        moveCamera(CameraUpdateFactory.newLatLng(LatLng(DEFAULT_LATITUDE, DEFAULT_LONGITUDE)))
        animateCamera(CameraUpdateFactory.zoomTo(9F))
    }
}

fun setupInfoWindow(context: Context, googleMap: GoogleMap?, onInfoWindowClick:(Marker) -> Unit, onPermissionGranted:(Marker) -> Unit) {
    googleMap?.apply {
        setInfoWindowAdapter(MyCustomInfoWindowAdapter(context))
        setOnInfoWindowCloseListener { marker ->
            val tag: MarkerTag = marker.tag as MarkerTag
            if(tag.type==0) {
                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_pin))
            }
        }
        setOnInfoWindowClickListener { marker ->
            if (AppHelper.checkLocationSetting(context)) {
                if (checkGrantedPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)) {
                    if (checkGrantedPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                        onInfoWindowClick(marker)
                    } else {
                        requestAppPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) {
                            onPermissionGranted(marker)
                        }
                    }
                } else {
                    requestAppPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) {
                        onPermissionGranted(marker)
                    }
                }
            }
        }
    }
}

fun setupMarkerAnimation(googleMap: GoogleMap?) {
    googleMap?.setOnMarkerClickListener {
        val tag: MarkerTag = it.tag as MarkerTag
        if (tag.type == 0) {
            val start = SystemClock.uptimeMillis()
            val duration = 1200L

            val handler = Handler()
            val anim = BounceAnimation(start, duration, it, handler)
            handler.post(anim)
        }
        if (tag.type==3) {
            it.hideInfoWindow()
        }else {
            it.showInfoWindow()

        }
        return@setOnMarkerClickListener true
    }
}