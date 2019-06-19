package com.yoesuv.infomadiun.menu.maps.views

import android.annotation.SuppressLint
import android.app.Activity
import androidx.lifecycle.Observer
import android.content.Intent
import android.content.IntentSender
import androidx.databinding.DataBindingUtil
import android.graphics.Color
import android.os.*
import androidx.fragment.app.Fragment
import android.util.Log
import android.util.TypedValue
import android.view.*
import com.akexorcist.googledirection.DirectionCallback
import com.akexorcist.googledirection.GoogleDirection
import com.akexorcist.googledirection.constant.AvoidType
import com.akexorcist.googledirection.constant.TransportMode
import com.akexorcist.googledirection.model.Direction
import com.akexorcist.googledirection.model.Route
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
import com.yoesuv.infomadiun.App
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.data.Constants
import com.yoesuv.infomadiun.databinding.FragmentMapBinding
import com.yoesuv.infomadiun.menu.maps.adapters.MyCustomInfoWindowAdapter
import com.yoesuv.infomadiun.menu.maps.models.PinModel
import com.yoesuv.infomadiun.menu.maps.viewmodels.FragmentMapsViewModel
import com.yoesuv.infomadiun.utils.AppHelper
import com.yoesuv.infomadiun.utils.BounceAnimation
import kotlinx.android.synthetic.main.fragment_map.view.*

/**
 *  Created by yusuf on 4/30/18.
 */
class FragmentMaps: Fragment(), OnMapReadyCallback, DirectionCallback {

    companion object {
        const val REQUEST_FEATURE_LOCATION_PERMISSION_CODE:Int = 12
        const val PREFERENCE_LATITUDE = "preference_latitude"
        const val PREFERENCE_LONGITUDE = "preference_longitude"
        fun getInstance(): Fragment {
            return FragmentMaps()
        }
    }

    private lateinit var binding: FragmentMapBinding
    private lateinit var viewModel: FragmentMapsViewModel

    private lateinit var activity: Activity
    private var googleMap: GoogleMap? = null
    private lateinit var rxPermission: RxPermissions
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var markerLocation: Marker? = null
    private lateinit var googleApiClient: GoogleApiClient
    private var myLocationCallback: MyLocationCallback? = null

    private lateinit var origin: LatLng
    private lateinit var destination: LatLng
    private val colors = arrayListOf("#7F2196f3","#7F4CAF50","#7FF44336")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this.getActivity()!!.applicationContext)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false)
        viewModel = FragmentMapsViewModel()
        binding.maps = viewModel

        activity = getActivity() as Activity
        rxPermission = RxPermissions(activity)

        val mapFragment:SupportMapFragment? = childFragmentManager.findFragmentById(R.id.mapLocation) as SupportMapFragment
        mapFragment?.getMapAsync(this)

        setHasOptionsMenu(true)
        binding.textViewGettingDirection.visibility = View.INVISIBLE

        viewModel.listPin.observe(this, Observer { listPin ->
            onListDataChanged(listPin!!)
        })
        viewModel.error.observe(this, Observer {
            AppHelper.displayErrorToast(activity, getString(R.string.ops_message))
        })

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.destroy()
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
                AppHelper.displayErrorToast(context!!, getString(R.string.location_setting_off))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_map, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId==R.id.menuMapRefresh){
            googleMap?.clear()
            //default location
            googleMap?.moveCamera(CameraUpdateFactory.newLatLng(LatLng(-7.813882, 111.371713)))
            googleMap?.animateCamera(CameraUpdateFactory.zoomTo(9f))
            viewModel.getListPin()
        }
        return super.onOptionsItemSelected(item)
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
                val response: LocationSettingsResponse? = task.getResult(ApiException::class.java)
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
                            AppHelper.displayErrorToast(activity, getString(R.string.access_location_denied))
                        }
                    }
        }else{
            enableUserLocation(googleMap)
        }
    }

    @SuppressLint("MissingPermission")
    private fun enableUserLocation(googleMap: GoogleMap?){

        myLocationCallback = MyLocationCallback(googleMap)

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

            destination = LatLng(tag.latitude!!, tag.longitude!!)
            val latitude: String? = App.prefHelper?.getString(PREFERENCE_LATITUDE)
            val longitude: String? = App.prefHelper?.getString(PREFERENCE_LONGITUDE)

            if(latitude!=""){
                if(longitude!=""){
                    view?.textViewGettingDirection?.visibility = View.VISIBLE
                    origin = LatLng(latitude!!.toDouble(), longitude!!.toDouble())
                    GoogleDirection.withServerKey(activity.getString(R.string.info_madiun_google_maps_api_key))
                            .from(origin)
                            .to(destination)
                            .alternativeRoute(true)
                            .transportMode(TransportMode.DRIVING)
                            .avoid(AvoidType.TOLLS)
                            .execute(this)
                } else {
                    AppHelper.displayErrorToast(activity, getString(R.string.error_get_user_location))
                }
            } else {
                AppHelper.displayErrorToast(activity, getString(R.string.error_get_user_location))
            }
        }
    }

    private fun setCameraWithCoordinationBounds(route: Route){
        val southwest:LatLng = route.bound.southwestCoordination.coordination
        val northeast:LatLng = route.bound.northeastCoordination.coordination
        val bounds = LatLngBounds(southwest, northeast)
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100))

    }

    private fun onListDataChanged(listPin: MutableList<PinModel>){
        if(listPin.isNotEmpty()){
            for(pinModel in listPin){
                val markerOptions = MarkerOptions()
                markerOptions.position(LatLng(pinModel.latitude!!, pinModel.longitude!!))
                markerOptions.title(pinModel.name)
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_pin))
                markerLocation = googleMap?.addMarker(markerOptions)
                markerLocation?.tag = MarkerTag(pinModel.name!!, 0, pinModel.latitude, pinModel.longitude)
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap?.clear()
        val paddingBottom = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 108F, resources.displayMetrics))
        googleMap?.setPadding(0, 0, 0, paddingBottom)
        googleMap?.uiSettings?.isZoomControlsEnabled = true
        googleMap?.uiSettings?.isCompassEnabled = true

        //default location
        googleMap?.moveCamera(CameraUpdateFactory.newLatLng(LatLng(-7.813882, 111.371713)))
        googleMap?.animateCamera(CameraUpdateFactory.zoomTo(9f))
        googleMap?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.style_map))

        this.googleMap = googleMap
        viewModel.getListPin()

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
            val tag: MarkerTag = it.tag as MarkerTag
            Log.d(Constants.TAG_DEBUG,"FragmentMaps # Marker tag type : ${tag.type}")
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

    override fun onDirectionSuccess(direction: Direction?, rawBody: String?) {
        Log.e(Constants.TAG_ERROR,"FragmentMaps # onDirectionSuccess ${direction?.errorMessage}")
        view?.textViewGettingDirection?.visibility = View.INVISIBLE
        if(direction!!.isOK){
            Log.d(Constants.TAG_DEBUG,"FragmentMaps # found ${direction.routeList.size} direction")
            if(direction.routeList.size>0) {
                googleMap?.clear()
                markerLocation = googleMap?.addMarker(MarkerOptions().position(destination).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_pin_selected)))
                markerLocation?.tag = MarkerTag("Destination", 3, 0.0, 0.0)

                setCameraWithCoordinationBounds(direction.routeList[0])

                for (i: Int in 0 until direction.routeList.size) {
                    val color = colors[i % colors.size]
                    val route = direction.routeList[i]
                    val directionPositionList = route.legList[0].directionPoint
                    googleMap?.addPolyline(DirectionConverter.createPolyline(context, directionPositionList, 5, Color.parseColor(color)))
                }
            }
        }else{
            AppHelper.displayErrorToast(activity, getString(R.string.error_get_direction))
        }
    }

    override fun onDirectionFailure(t: Throwable?) {
        AppHelper.displayErrorToast(activity, getString(R.string.error_get_direction))
    }

    class MarkerTag(val title:String, val type:Int, val latitude:Double?, val longitude:Double?)

    class MyLocationCallback(private val googleMap: GoogleMap?) : LocationCallback(){

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

                App.prefHelper?.setString(PREFERENCE_LATITUDE, listLocation[0].latitude.toString())
                App.prefHelper?.setString(PREFERENCE_LONGITUDE, listLocation[0].longitude.toString())
            }
        }
    }

}