package com.yoesuv.infomadiun.menu.maps.views

import android.Manifest
import android.annotation.SuppressLint
import androidx.databinding.DataBindingUtil
import android.graphics.Color
import android.os.*
import androidx.fragment.app.Fragment
import android.util.TypedValue
import android.view.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
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
import com.yoesuv.infomadiun.databinding.FragmentMapBinding
import com.yoesuv.infomadiun.menu.maps.adapters.MyCustomInfoWindowAdapter
import com.yoesuv.infomadiun.menu.maps.models.MarkerTag
import com.yoesuv.infomadiun.menu.maps.models.PinModel
import com.yoesuv.infomadiun.menu.maps.viewmodels.FragmentMapsViewModel
import com.yoesuv.infomadiun.utils.*
import kotlin.math.roundToInt

/**
 *  Updated by yusuf on 03 March 2023.
 */
class FragmentMaps : Fragment(), OnMapReadyCallback, MenuProvider, DirectionCallback {

    companion object {
        const val PREFERENCE_LATITUDE = "preference_latitude"
        const val PREFERENCE_LONGITUDE = "preference_longitude"
    }

    private lateinit var binding: FragmentMapBinding
    private val viewModel: FragmentMapsViewModel by activityViewModels()

    private var googleMap: GoogleMap? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var markerLocation: Marker? = null
    private lateinit var myLocationCallback: MyLocationCallback

    private lateinit var origin: LatLng
    private lateinit var destination: LatLng
    private val colors = arrayListOf("#7F2196f3", "#7F4CAF50", "#7FF44336")
    private lateinit var menuHost: MenuHost

    private val requestPermissionLocation =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                enableUserLocation(googleMap)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this.requireActivity().applicationContext)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false)
        binding.lifecycleOwner = this
        binding.maps = viewModel

        val mapFragment: SupportMapFragment = childFragmentManager.findFragmentById(R.id.mapLocation) as SupportMapFragment
        mapFragment.getMapAsync(this)

        menuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textViewGettingDirection.visibility = View.INVISIBLE
        viewModel.listPin.observe(viewLifecycleOwner) { listPin ->
            onListDataChanged(listPin)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        LocationServices.getFusedLocationProviderClient(requireActivity()).removeLocationUpdates(myLocationCallback)
    }

    @SuppressLint("MissingPermission")
    private fun enableUserLocation(googleMap: GoogleMap?) {
        myLocationCallback = MyLocationCallback(googleMap)

        googleMap?.uiSettings?.isMyLocationButtonEnabled = true
        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 2000)
            .setWaitForAccurateLocation(false)
            .build()
        fusedLocationClient.requestLocationUpdates(locationRequest, myLocationCallback, Looper.getMainLooper())
    }

    private fun getDirection(marker: Marker?) {
        val tag: MarkerTag = marker?.tag as MarkerTag
        if (tag.type == 0) {
            tag.latitude?.let { lat ->
                tag.longitude?.let { lng ->
                    destination = LatLng(lat, lng)
                    val latitude: String? = App.prefHelper?.getString(PREFERENCE_LATITUDE)
                    val longitude: String? = App.prefHelper?.getString(PREFERENCE_LONGITUDE)
                    if (!latitude.isNullOrEmpty()) {
                        if (!longitude.isNullOrEmpty()) {
                            binding.textViewGettingDirection.visibility = View.VISIBLE
                            origin = LatLng(latitude.toDouble(), longitude.toDouble())
                            GoogleDirection.withServerKey(requireContext().getString(R.string.DIRECTION_API_KEY))
                                .from(origin)
                                .to(destination)
                                .alternativeRoute(true)
                                .transportMode(TransportMode.DRIVING)
                                .avoid(AvoidType.TOLLS)
                                .execute(this)
                        } else {
                            AppHelper.displayErrorToast(requireContext(), R.string.error_get_user_location)
                        }
                    } else {
                        AppHelper.displayErrorToast(requireContext(), R.string.error_get_user_location)
                    }
                }
            }
        }
    }

    private fun setCameraWithCoordinationBounds(route: Route) {
        val southwest: LatLng = route.bound.southwestCoordination.coordination
        val northeast: LatLng = route.bound.northeastCoordination.coordination
        val bounds = LatLngBounds(southwest, northeast)
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100))
    }

    private fun onListDataChanged(listPin: List<PinModel>) {
        if (listPin.isNotEmpty()) {
            for (pinModel in listPin) {
                val markerOptions = MarkerOptions()
                markerOptions.position(LatLng(pinModel.latitude!!, pinModel.longitude!!))
                markerOptions.title(pinModel.name)
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_pin))
                markerLocation = googleMap?.addMarker(markerOptions)
                markerLocation?.tag = MarkerTag(pinModel.name!!, 0, pinModel.latitude, pinModel.longitude)
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        setDefaultLocation(googleMap)
        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(requireContext(), R.raw.style_map))
        val paddingBottom = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 108F, resources.displayMetrics).roundToInt()
        googleMap.setPadding(0, 0, 0, paddingBottom)
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.uiSettings.isCompassEnabled = true

        //default location
        this.googleMap = googleMap
        viewModel.getListPin()

        if (AppHelper.checkLocationSetting(requireContext())) {
            requestPermissionLocation.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            AppHelper.displayLocationSettingsRequest(requireActivity())
        }

        googleMap.setInfoWindowAdapter(MyCustomInfoWindowAdapter(context))
        googleMap.setOnInfoWindowCloseListener { marker ->
            val tag: MarkerTag = marker.tag as MarkerTag
            if (tag.type == 0) {
                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_pin))
            }
        }
        googleMap.setOnInfoWindowClickListener { marker ->
            if (AppHelper.checkLocationSetting(requireContext())) {
                if (checkGrantedPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)) {
                    getDirection(marker)
                } else {
                    requestPermissionLocation.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                }
            } else {
                AppHelper.displayErrorToast(requireContext(), R.string.location_setting_off)
                openAppSettings(requireContext())
            }
        }
        setupMarkerAnimation(googleMap)
    }

    override fun onCreateMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_map, menu)
    }

    override fun onMenuItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuMapRefresh) {
            setDefaultLocation(googleMap)
            viewModel.getListPin()
        }
        return false
    }

    override fun onDirectionSuccess(direction: Direction?) {
        binding.textViewGettingDirection.visibility = View.INVISIBLE
        direction?.let { dir ->
            if (dir.isOK) {
                if (dir.routeList.size > 0) {
                    googleMap?.clear()
                    markerLocation =
                        googleMap?.addMarker(MarkerOptions().position(destination).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_pin_selected)))
                    markerLocation?.tag = MarkerTag("Destination", 3, 0.0, 0.0)

                    setCameraWithCoordinationBounds(dir.routeList[0])

                    for (i: Int in 0 until dir.routeList.size) {
                        val color = colors[i % colors.size]
                        val route = dir.routeList[i]
                        val directionPositionList = route.legList[0].directionPoint
                        googleMap?.addPolyline(DirectionConverter.createPolyline(requireContext(), directionPositionList, 5, Color.parseColor(color)))
                    }
                }
            } else {
                logError("FragmentMaps # direction not ok ${direction.errorMessage}")
                AppHelper.displayErrorToast(requireContext(), R.string.error_get_direction)
            }
        }
    }

    override fun onDirectionFailure(t: Throwable) {
        logError("FragmentMaps # onDirectionFailure ${t.message}")
        AppHelper.displayErrorToast(requireContext(), R.string.error_get_direction)
        t.printStackTrace()
    }

}