package com.yoesuv.infomadiun.menu.maps.views

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.graphics.Color
import android.os.*
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akexorcist.googledirection.DirectionCallback
import com.akexorcist.googledirection.GoogleDirection
import com.akexorcist.googledirection.constant.AvoidType
import com.akexorcist.googledirection.constant.TransportMode
import com.akexorcist.googledirection.model.Direction
import com.akexorcist.googledirection.util.DirectionConverter
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
import com.yoesuv.infomadiun.utils.BounceAnimation
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.custom_info_window.view.*

/**
 *  Created by yusuf on 4/30/18.
 */
class FragmentMaps: Fragment(), OnMapReadyCallback, DirectionCallback, MapContract.ViewMaps {

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
    private var markerLocation: Marker? = null
    private lateinit var googleApiClient: GoogleApiClient
    private var myLocationCallback: MyLocationCallback? = null

    private lateinit var origin: LatLng
    private val colors = arrayListOf("#7fff7272","#7f31c7c5","#7fff8a00")

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

        origin = LatLng(-7.813882, 111.371713)
        myLocationCallback = MyLocationCallback(googleMap, origin)

        googleMap?.uiSettings?.isMyLocationButtonEnabled = true
        val locationRequest:LocationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 2000
        locationRequest.fastestInterval = 1000
        fusedLocationClient.requestLocationUpdates(locationRequest, myLocationCallback, Looper.myLooper())
    }

    private fun getDirection(marker: Marker?){
        val tag: MarkerTag = marker?.tag as MarkerTag
        if(tag.type==0){
            GoogleDirection.withServerKey(activity.getString(R.string.server_key))
                    .from(origin)
                    .to(LatLng(tag.latitude!!, tag.longitude!!))
                    .alternativeRoute(true)
                    .transportMode(TransportMode.DRIVING)
                    .avoid(AvoidType.TOLLS)
                    .execute(this)

        }
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
        googleMap?.setOnInfoWindowClickListener {
            getDirection(it)
        }
        googleMap?.setOnMarkerClickListener {
            Log.d(Constants.TAG_DEBUG,"FragmentMaps # marker clicked")
            it.showInfoWindow()

            val tag: MarkerTag = it.tag as MarkerTag
            if (tag.type==0) {
                val start = SystemClock.uptimeMillis()
                val duration = 1200L

                val handler = Handler()
                val anim = BounceAnimation(start, duration, it, handler)
                handler.post(anim)
            }

            return@setOnMarkerClickListener true
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
                markerLocation = googleMap?.addMarker(markerOptions)
                markerLocation?.tag = MarkerTag(listPin[i].name!!, 0, listPin[i].latitude, listPin[i].longitude)
            }
        }
    }

    override fun onDirectionSuccess(direction: Direction?, rawBody: String?) {
        Log.e(Constants.TAG_ERROR,"FragmentMaps # onDirectionSuccess ${direction?.errorMessage}")
        if(direction!!.isOK){
            var polyLine: Polyline? = null
            for(i:Int in 0..(direction.routeList.size-1)){
                val color = colors[i % colors.size]
                val route = direction.routeList[i]
                val directionPositionList = route.legList[0].directionPoint
                polyLine?.remove()
                polyLine = googleMap?.addPolyline(DirectionConverter.createPolyline(context, directionPositionList, 5, Color.parseColor(color)))

            }
        }else{
            Toasty.error(activity, "Oops.. error mendapatkan petunjuk arah")
        }
    }

    override fun onDirectionFailure(t: Throwable?) {

    }

    class MarkerTag(val title:String, val type:Int, val latitude:Double?, val longitude:Double?)

    class MyLocationCallback(private val googleMap: GoogleMap?, private var origin:LatLng?) : LocationCallback(){

        private var markerUser: Marker? = null

        override fun onLocationResult(locationResult: LocationResult?) {
            super.onLocationResult(locationResult)
            val listLocation = locationResult?.locations
            if (listLocation?.isNotEmpty()!!){
                Log.d(Constants.TAG_DEBUG,"FragmentMaps # found ${listLocation.size} location")
                val markerOpt = MarkerOptions()
                markerOpt.position(LatLng(listLocation[0].latitude, listLocation[0].longitude))
                markerOpt.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_user_position))
                markerUser?.remove()
                markerUser = googleMap?.addMarker(markerOpt)
                markerUser?.tag = MarkerTag("Lokasi Anda", 1, listLocation[0].latitude, listLocation[0].longitude)
                origin = LatLng(listLocation[0].latitude, listLocation[0].longitude)
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