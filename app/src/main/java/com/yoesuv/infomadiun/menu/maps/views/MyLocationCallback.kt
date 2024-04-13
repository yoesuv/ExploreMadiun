package com.yoesuv.infomadiun.menu.maps.views

import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.yoesuv.infomadiun.App
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.menu.maps.models.MarkerTag

class MyLocationCallback(private val googleMap: GoogleMap?) : LocationCallback() {

    private var markerUser: Marker? = null

    override fun onLocationResult(locationResult: LocationResult) {
        super.onLocationResult(locationResult)
        val listLocation = locationResult.locations
        if (listLocation.isNotEmpty()) {
            val markerOpt = MarkerOptions()
            markerOpt.position(LatLng(listLocation[0].latitude, listLocation[0].longitude))
            markerOpt.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_user_position))
            markerUser?.remove()
            markerUser = googleMap?.addMarker(markerOpt)
            markerUser?.tag = MarkerTag(
                "Lokasi Anda",
                1,
                listLocation[0].latitude,
                listLocation[0].longitude
            )

            App.prefHelper?.setString(FragmentMaps.PREFERENCE_LATITUDE, listLocation[0].latitude.toString())
            App.prefHelper?.setString(FragmentMaps.PREFERENCE_LONGITUDE, listLocation[0].longitude.toString())
        }
    }
}