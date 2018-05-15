package com.yoesuv.infomadiun.menu.maps.views

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.support.v4.app.Fragment
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
import com.google.android.gms.maps.model.*
import com.google.android.gms.tasks.Task
import com.tbruyelle.rxpermissions2.RxPermissions
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.data.Constants
import com.yoesuv.infomadiun.menu.maps.contracts.MapContract
import com.yoesuv.infomadiun.menu.maps.models.PinModel
import com.yoesuv.infomadiun.menu.maps.presenters.MapPresenter
import com.yoesuv.infomadiun.utils.AppHelper
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.custom_info_window.view.*

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
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var markerUser: Marker? = null
    private lateinit var googleApiClient: GoogleApiClient
    private var myLocationCallback: MyLocationCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this.getActivity()!!.applicationContext)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_map, container, false)

        mapPresenter = MapPresenter(this)
        activity = getActivity() as Activity
        val toolbar = activity.findViewById<Toolbar>(R.id.toolbarMain)
        toolbar.textViewToolbar.text = getString(R.string.menu_maps)

        rxPermission = RxPermissions(activity)

        val mapFragment:SupportMapFragment? = childFragmentManager.findFragmentById(R.id.mapLocation) as SupportMapFragment
        mapFragment?.getMapAsync(this)

        return v
    }

    override fun onDestroy() {
        super.onDestroy()
        mapPresenter.destroy()
        if(myLocationCallback!=null) {
            LocationServices.getFusedLocationProviderClient(activity).removeLocationUpdates(myLocationCallback)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==REQUEST_FEATURE_LOCATION_PERMISSION_CODE){
            if(resultCode==Activity.RESULT_OK){
                //setup user location
                requestPermission(googleMap)
            }else if(resultCode==Activity.RESULT_CANCELED){
                Toasty.error(activity, getString(R.string.location_setting_off)).show()
            }
        }
    }

    private fun displayLocationSettingsRequest(){
        googleApiClient = GoogleApiClient.Builder(activity.applicationContext).addApi(LocationServices.API).build()
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

    private fun requestPermission(googleMap: GoogleMap?){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            rxPermission.request(android.Manifest.permission.ACCESS_FINE_LOCATION)
                    .subscribe { t: Boolean? ->
                        if(t!!){
                            enableUserLocation(googleMap)
                        }else{
                            Toasty.error(activity, getString(R.string.access_location_denied)).show()
                        }
                    }
        }else{
            enableUserLocation(googleMap)
        }
    }

    @SuppressLint("MissingPermission")
    private fun enableUserLocation(googleMap: GoogleMap?){

        myLocationCallback = MyLocationCallback(googleMap, markerUser)

        googleMap?.uiSettings?.isMyLocationButtonEnabled = true
        val locationRequest:LocationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 2000
        locationRequest.fastestInterval = 1000
        fusedLocationClient.requestLocationUpdates(locationRequest, myLocationCallback, Looper.myLooper())
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap?.clear()
        googleMap?.uiSettings?.isCompassEnabled = true

        //default location
        googleMap?.moveCamera(CameraUpdateFactory.newLatLng(LatLng(-7.813882, 111.371713)))
        googleMap?.animateCamera(CameraUpdateFactory.zoomTo(9f))
        googleMap?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.style_map))

        this.googleMap = googleMap
        mapPresenter.getListPin()

        if(AppHelper.checkLocationSetting(activity)){
            requestPermission(googleMap)
        }else{
            displayLocationSettingsRequest()
        }

        googleMap?.setInfoWindowAdapter(MyCustomInfoWindowAdapter(activity))
        googleMap?.setOnInfoWindowCloseListener {
            val tag: MarkerTag = it.tag as MarkerTag
            if(tag.type==0) {
                it.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_pin))
            }
        }
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    override fun setData(listPin: MutableList<PinModel>) {
        if(listPin.isNotEmpty()){
            for(i:Int in 0..listPin.size){
                val markerOptions = MarkerOptions()
                markerOptions.position(LatLng(listPin[i].latitude!!, listPin[i].longitude!!))
                markerOptions.title(listPin[i].name)
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_pin))
                markerUser = googleMap?.addMarker(markerOptions)
                markerUser?.tag = MarkerTag(listPin[i].name!!, 0)
            }
        }
    }

    class MarkerTag(val title:String, val type:Int)

    class MyLocationCallback(private val googleMap: GoogleMap?, private var markerUser: Marker?) : LocationCallback(){

        override fun onLocationResult(locationResult: LocationResult?) {
            super.onLocationResult(locationResult)
            val listLocation = locationResult?.locations
            if (listLocation?.isNotEmpty()!!){
                Log.d(Constants.TAG_DEBUG,"FragmentMaps # found ${listLocation.size} location")
                val markerOpt = MarkerOptions()
                markerOpt.position(LatLng(listLocation[0].latitude, listLocation[0].longitude))
                markerOpt.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_user_position))
                markerUser = googleMap?.addMarker(markerOpt)
                markerUser?.tag = MarkerTag("Lokasi Anda", 1)
            }
        }
    }

    class MyCustomInfoWindowAdapter(private val activity: Activity?) : GoogleMap.InfoWindowAdapter{

        private val mContents:View = LayoutInflater.from(activity?.applicationContext).inflate(R.layout.custom_info_window, null)

        override fun getInfoContents(marker: Marker?): View {
            return mContents
        }

        override fun getInfoWindow(marker: Marker?): View {
            val tag: MarkerTag = marker?.tag as MarkerTag
            if(tag.type==1){
                mContents.textViewMapLocationName.text = activity?.getString(R.string.your_location)
                mContents.imageViewMapLocationDirection.visibility = View.GONE
            }else {
                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_pin_selected))
                mContents.textViewMapLocationName.text = marker.title
                mContents.imageViewMapLocationDirection.visibility = View.VISIBLE
            }
            return mContents
        }

    }

}