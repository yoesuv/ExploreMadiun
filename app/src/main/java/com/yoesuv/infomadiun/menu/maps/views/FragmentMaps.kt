package com.yoesuv.infomadiun.menu.maps.views

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.Task
import com.tbruyelle.rxpermissions2.RxPermissions
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.data.Constants
import com.yoesuv.infomadiun.menu.maps.contracts.MapContract
import com.yoesuv.infomadiun.menu.maps.models.PinModel
import com.yoesuv.infomadiun.menu.maps.presenters.MapPresenter
import kotlinx.android.synthetic.main.activity_main.view.*

/**
 *  Created by yusuf on 4/30/18.
 */
class FragmentMaps: Fragment(), OnMapReadyCallback, MapContract.ViewMaps {

    companion object {
        const val REQUEST_FEATURE_LOCATION_PERMISSION_CODE:Int = 12
        fun getInstance():Fragment{
            return FragmentMaps()
        }
    }

    private lateinit var activity: Activity
    private lateinit var mapPresenter: MapPresenter
    private var googleMap: GoogleMap? = null
    private lateinit var rxPermission: RxPermissions

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_map, container, false)

        mapPresenter = MapPresenter(this)

        activity = getActivity() as Activity
        val toolbar = activity.findViewById<Toolbar>(R.id.toolbarMain)
        toolbar.textViewToolbar.text = getString(R.string.menu_maps)

        rxPermission = RxPermissions(activity)
        displayLocationSettingsRequest()
        requestPermission()

        val mapFragment:SupportMapFragment? = childFragmentManager.findFragmentById(R.id.mapLocation) as SupportMapFragment
        mapFragment?.getMapAsync(this)

        return v
    }

    override fun onDestroy() {
        super.onDestroy()
        mapPresenter.destroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==REQUEST_FEATURE_LOCATION_PERMISSION_CODE){
            Log.d(Constants.TAG_DEBUG,"FragmentMaps # onActivityResult => request feature location")
            if(resultCode==Activity.RESULT_OK){
                Log.d(Constants.TAG_DEBUG,"FragmentMaps # onActivityResult => request feature location OK")
                //setup user location
            }else if(resultCode==Activity.RESULT_CANCELED){
                Log.d(Constants.TAG_DEBUG,"FragmentMaps # onActivityResult => request feature location CANCELED")
            }
        }
    }

    private fun displayLocationSettingsRequest(){
        val googleApiClient = GoogleApiClient.Builder(activity.applicationContext).addApi(LocationServices.API).build()
        googleApiClient.connect()

        val locationRequest:LocationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 10000
        locationRequest.fastestInterval = 10000/2

        val builder:LocationSettingsRequest.Builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
        builder.setAlwaysShow(true)

        val result:Task<LocationSettingsResponse> = LocationServices.getSettingsClient(activity).checkLocationSettings(builder.build())
        result.addOnCompleteListener { task ->
            try {
                val response: LocationSettingsResponse = task.getResult(ApiException::class.java)
            }catch (ex:ApiException) {
                if(ex.statusCode==LocationSettingsStatusCodes.RESOLUTION_REQUIRED){
                    val resolvableApiException = ex as ResolvableApiException
                    try{
                        resolvableApiException.startResolutionForResult(activity, REQUEST_FEATURE_LOCATION_PERMISSION_CODE)
                    }catch (e: IntentSender.SendIntentException){
                        Log.e(Constants.TAG_ERROR, "FragmentMaps # RESOLUTION_REQUIRED ${e.message}")
                    }
                }else if(ex.statusCode==LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE){
                    Log.e(Constants.TAG_ERROR, "FragmentMaps # LocationSettings DISABLED")
                }
            }
        }
    }

    private fun requestPermission(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            rxPermission.request(android.Manifest.permission.ACCESS_FINE_LOCATION)
                    .subscribe { t: Boolean? ->
                        if(t!!){
                            Log.d(Constants.TAG_DEBUG,"FragmentMaps # requestPermission GRANTED")
                        }else{
                            Log.d(Constants.TAG_ERROR,"FragmentMaps # requestPermission DENIED")
                        }
                    }
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap?.clear()

        val settings = googleMap?.uiSettings
        settings?.isZoomControlsEnabled = true
        settings?.isZoomGesturesEnabled = true
        settings?.isCompassEnabled = true

        //default location
        googleMap?.moveCamera(CameraUpdateFactory.newLatLng(LatLng(-7.813882, 111.371713)))
        googleMap?.animateCamera(CameraUpdateFactory.zoomTo(9f))
        googleMap?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.style_map))

        this.googleMap = googleMap
        mapPresenter.getListPin()
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    override fun setData(listPin: MutableList<PinModel>) {
        if(listPin.isNotEmpty()){
            for(i:Int in 0..listPin.size){
                val marker = MarkerOptions()
                marker.position(LatLng(listPin[i].latitude!!, listPin[i].longitude!!))
                marker.title(listPin[i].name)
                marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_pin))
                googleMap?.addMarker(marker)
            }
        }
    }

}