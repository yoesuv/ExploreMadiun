package com.yoesuv.infomadiun.menu.other.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.menu.other.models.LicenseModel

class ChildFragmentLibrariesViewModel: ViewModel() {

    private var listLibraries: MutableList<LicenseModel> = mutableListOf()
    var listData: MutableLiveData<MutableList<LicenseModel>> = MutableLiveData()

    fun setupData(context: Context?){
        listLibraries.clear()
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

        listData.value = listLibraries
    }

}