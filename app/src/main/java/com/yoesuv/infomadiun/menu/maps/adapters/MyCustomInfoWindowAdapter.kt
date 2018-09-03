package com.yoesuv.infomadiun.menu.maps.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.menu.maps.views.FragmentMaps
import kotlinx.android.synthetic.main.custom_info_window.view.*

class MyCustomInfoWindowAdapter(private val activity: Activity?): GoogleMap.InfoWindowAdapter {

    private val mContents: View = LayoutInflater.from(activity?.applicationContext).inflate(R.layout.custom_info_window, null)

    override fun getInfoContents(marker: Marker?): View {
        return mContents
    }

    override fun getInfoWindow(marker: Marker?): View {
        val tag: FragmentMaps.MarkerTag = marker?.tag as FragmentMaps.MarkerTag
        if (tag.type == 1) {
            mContents.textViewMapLocationName.text = activity?.getString(R.string.your_location)
            mContents.imageViewMapLocationDirection.visibility = View.GONE
        } else {
            marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_pin_selected))
            mContents.textViewMapLocationName.text = marker.title
            mContents.imageViewMapLocationDirection.visibility = View.VISIBLE
        }
        return mContents
    }

}