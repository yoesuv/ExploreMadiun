package com.yoesuv.infomadiun.menu.maps.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.databinding.CustomInfoWindowBinding
import com.yoesuv.infomadiun.menu.maps.models.MarkerTag

class MyCustomInfoWindowAdapter(private val context: Context?) : GoogleMap.InfoWindowAdapter {

    private val binding = CustomInfoWindowBinding.inflate(LayoutInflater.from(context))

    override fun getInfoContents(marker: Marker): View {
        return binding.root
    }

    override fun getInfoWindow(marker: Marker): View {
        val tag: MarkerTag = marker.tag as MarkerTag
        if (tag.type == 1) {
            binding.textViewMapLocationName.text = context?.getString(R.string.your_location)
            binding.imageViewMapLocationDirection.visibility = View.GONE
        } else {
            marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_pin_selected))
            binding.textViewMapLocationName.text = marker.title
            binding.imageViewMapLocationDirection.visibility = View.VISIBLE
        }
        return binding.root
    }

}