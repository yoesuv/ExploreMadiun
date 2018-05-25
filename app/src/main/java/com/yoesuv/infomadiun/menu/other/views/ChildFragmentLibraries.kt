package com.yoesuv.infomadiun.menu.other.views

import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.menu.other.adapters.LicenseAdapter
import com.yoesuv.infomadiun.menu.other.models.LicenseModel
import kotlinx.android.synthetic.main.child_fragment_libraries.view.*

class ChildFragmentLibraries: Fragment() {

    companion object {
        fun getInstance(): Fragment{
            return ChildFragmentLibraries()
        }
    }

    private var listLibraries: MutableList<LicenseModel> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val icons8 = LicenseModel(context?.getString(R.string.icons8), context?.getString(R.string.icons8_url), context?.getString(R.string.icons8_license), false)
        val retrofit = LicenseModel(context?.getString(R.string.retrofit), context?.getString(R.string.retrofit_url), context?.getString(R.string.retrofit_license), false)
        val rxJava = LicenseModel(context?.getString(R.string.rxjava), context?.getString(R.string.rxjava_url), context?.getString(R.string.rxjava_license), false)
        val rxAndroid = LicenseModel(context?.getString(R.string.rxAndroid), context?.getString(R.string.rxAndroid_url), context?.getString(R.string.rxAndroid_license), false)
        val rxKotlin = LicenseModel(context?.getString(R.string.rx_kotlin), context?.getString(R.string.rx_kotlin_url), context?.getString(R.string.rx_kotlin_license), false)
        val glide = LicenseModel(context?.getString(R.string.glide), context?.getString(R.string.glide_url), context?.getString(R.string.glide_license), false)
        val photoView = LicenseModel(context?.getString(R.string.photoview), context?.getString(R.string.photoview_url), context?.getString(R.string.photoview_license), false)
        val rxPermission = LicenseModel(context?.getString(R.string.rx_permission), context?.getString(R.string.rx_permission_url), context?.getString(R.string.rx_permission_license), false)
        val toasty = LicenseModel(context?.getString(R.string.toasty), context?.getString(R.string.toasty_url), context?.getString(R.string.toasty_license), false)
        val navigationTabStrip = LicenseModel(context?.getString(R.string.navigation_tab_strip), context?.getString(R.string.navigation_tab_strip_url), context?.getString(R.string.navigation_tab_strip_license), false)
        val googleDirection = LicenseModel(context?.getString(R.string.google_direction), context?.getString(R.string.google_direction_url), context?.getString(R.string.google_direction_license), true)

        listLibraries.add(icons8)
        listLibraries.add(retrofit)
        listLibraries.add(rxJava)
        listLibraries.add(rxAndroid)
        listLibraries.add(rxKotlin)
        listLibraries.add(glide)
        listLibraries.add(photoView)
        listLibraries.add(rxPermission)
        listLibraries.add(toasty)
        listLibraries.add(navigationTabStrip)
        listLibraries.add(googleDirection)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View? = LayoutInflater.from(context).inflate(R.layout.child_fragment_libraries, container, false)

        view?.recyclerViewLicense?.layoutManager = LinearLayoutManager(context)

        val adapter = LicenseAdapter(context, listLibraries)
        view?.recyclerViewLicense?.adapter = adapter

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            view?.recyclerViewLicense?.isNestedScrollingEnabled = true
        }

        return view
    }

}