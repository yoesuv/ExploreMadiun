package com.yoesuv.infomadiun.menu.maps.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.data.Constants
import kotlinx.android.synthetic.main.activity_main.view.*

/**
 *  Created by yusuf on 4/30/18.
 */
class FragmentMaps: Fragment(), OnMapReadyCallback {

    companion object {
        fun getInstance():Fragment{
            return FragmentMaps()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_map, container, false)

        val activity: AppCompatActivity = activity as AppCompatActivity
        val toolbar = activity.findViewById<Toolbar>(R.id.toolbarMain)
        toolbar.textViewToolbar.text = getString(R.string.menu_maps)

        val mapFragment:SupportMapFragment? = childFragmentManager.findFragmentById(R.id.mapLocation) as SupportMapFragment
        mapFragment?.getMapAsync(this)

        return v
    }


    override fun onMapReady(googleMap: GoogleMap?) {
        Log.d(Constants.TAG_DEBUG,"FragmentMaps # Map Ready")

        val marker = MarkerOptions()
        marker.position(LatLng(0.0,0.0))
        marker.title("Marker")
        googleMap?.addMarker(marker)

        val settings = googleMap?.uiSettings
        settings?.isZoomControlsEnabled = true
        settings?.isZoomGesturesEnabled = true
        settings?.isCompassEnabled = true
    }

}