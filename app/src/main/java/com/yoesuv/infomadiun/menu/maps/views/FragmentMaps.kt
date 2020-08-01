package com.yoesuv.infomadiun.menu.maps.views

import android.annotation.SuppressLint
import android.app.Activity
import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import android.graphics.Color
import android.os.*
import androidx.fragment.app.Fragment
import android.util.TypedValue
import android.view.*
import androidx.fragment.app.activityViewModels
import com.akexorcist.googledirection.DirectionCallback
import com.akexorcist.googledirection.GoogleDirection
import com.akexorcist.googledirection.constant.AvoidType
import com.akexorcist.googledirection.constant.TransportMode
import com.akexorcist.googledirection.model.Direction
import com.akexorcist.googledirection.model.Route
import com.akexorcist.googledirection.util.DirectionConverter
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.yoesuv.infomadiun.App
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.data.DEFAULT_LATITUDE
import com.yoesuv.infomadiun.data.DEFAULT_LONGITUDE
import com.yoesuv.infomadiun.databinding.FragmentMapBinding
import com.yoesuv.infomadiun.menu.maps.adapters.MyCustomInfoWindowAdapter
import com.yoesuv.infomadiun.menu.maps.models.MarkerTag
import com.yoesuv.infomadiun.menu.maps.models.PinModel
import com.yoesuv.infomadiun.menu.maps.viewmodels.FragmentMapsViewModel
import com.yoesuv.infomadiun.utils.AppHelper
import com.yoesuv.infomadiun.utils.BounceAnimation
import com.yoesuv.infomadiun.utils.logError

/**
 *  Created by yusuf on 4/30/18.
 */
class FragmentMaps: Fragment(), OnMapReadyCallback, DirectionCallback {

    companion object {
        const val PREFERENCE_LATITUDE = "preference_latitude"
        const val PREFERENCE_LONGITUDE = "preference_longitude"
    }

    private lateinit var binding: FragmentMapBinding
    private val viewModel : FragmentMapsViewModel by activityViewModels()

    private lateinit var activity: Activity
    private var googleMap: GoogleMap? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var markerLocation: Marker? = null
    private var myLocationCallback: MyLocationCallback? = null

    private lateinit var origin: LatLng
    private lateinit var destination: LatLng
    private val colors = arrayListOf("#7F2196f3","#7F4CAF50","#7FF44336")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this.requireActivity().applicationContext)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false)
        binding.lifecycleOwner = this
        binding.maps = viewModel

        activity = getActivity() as Activity

        val mapFragment:SupportMapFragment? = childFragmentManager.findFragmentById(R.id.mapLocation) as SupportMapFragment
        mapFragment?.getMapAsync(this)

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textViewGettingDirection.visibility = View.INVISIBLE

        viewModel.listPin.observe(viewLifecycleOwner, Observer { listPin ->
            onListDataChanged(listPin!!)
        })
        viewModel.error.observe(viewLifecycleOwner, Observer {
            AppHelper.displayErrorToast(activity, getString(R.string.ops_message))
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        if(myLocationCallback!=null) {
            LocationServices.getFusedLocationProviderClient(activity).removeLocationUpdates(myLocationCallback)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_map, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menuMapRefresh){
            googleMap?.apply {
                clear()
                //default location
                moveCamera(CameraUpdateFactory.newLatLng(LatLng(DEFAULT_LATITUDE, DEFAULT_LONGITUDE)))
                animateCamera(CameraUpdateFactory.zoomTo(9f))
            }
            viewModel.getListPin()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun requestPermission(googleMap: GoogleMap?){

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
                    binding.textViewGettingDirection.visibility = View.VISIBLE
                    origin = LatLng(latitude!!.toDouble(), longitude!!.toDouble())
                    GoogleDirection.withServerKey(activity.getString(R.string.DIRECTION_API_KEY))
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
        googleMap?.moveCamera(CameraUpdateFactory.newLatLng(LatLng(DEFAULT_LATITUDE, DEFAULT_LONGITUDE)))
        googleMap?.animateCamera(CameraUpdateFactory.zoomTo(9f))
        googleMap?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.style_map))

        this.googleMap = googleMap
        viewModel.getListPin()

        if (AppHelper.checkLocationSetting(requireContext())) {
            requestPermission(googleMap)
        } else {
            AppHelper.displayLocationSettingsRequest(requireActivity())
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
        binding.textViewGettingDirection.visibility = View.INVISIBLE
        if(direction!!.isOK){
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
            logError("FragmentMaps # direction not ok ${direction.errorMessage}")
            AppHelper.displayErrorToast(activity, getString(R.string.error_get_direction))
        }
    }

    override fun onDirectionFailure(t: Throwable?) {
        logError("FragmentMaps # onDirectionFailure ${t?.message}")
        AppHelper.displayErrorToast(activity, getString(R.string.error_get_direction))
        t?.printStackTrace()
    }

}