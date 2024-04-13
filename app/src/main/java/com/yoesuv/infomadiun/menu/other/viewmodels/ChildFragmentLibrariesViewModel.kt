package com.yoesuv.infomadiun.menu.other.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.content.Context
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.menu.other.models.LicenseModel

class ChildFragmentLibrariesViewModel : ViewModel() {

    private var listLibraries: MutableList<LicenseModel> = mutableListOf()
    var listData: MutableLiveData<MutableList<LicenseModel>> = MutableLiveData()

    fun setupData(context: Context?) {
        listLibraries.clear()
        val icons8 =
            LicenseModel(context?.getString(R.string.icons8), context?.getString(R.string.icons8_url), context?.getString(R.string.icons8_license), false)
        val retrofit =
            LicenseModel(context?.getString(R.string.retrofit), context?.getString(R.string.retrofit_url), context?.getString(R.string.retrofit_license), false)
        val glide = LicenseModel(context?.getString(R.string.glide), context?.getString(R.string.glide_url), context?.getString(R.string.glide_license), false)
        val googleDirection = LicenseModel(
            context?.getString(R.string.google_direction),
            context?.getString(R.string.google_direction_url),
            context?.getString(R.string.google_direction_license),
            false
        )
        val sdp = LicenseModel(
            context?.getString(R.string.sdp_android),
            context?.getString(R.string.sdp_android_url),
            context?.getString(R.string.sdp_android_license),
            false
        )
        val ssp = LicenseModel(
            context?.getString(R.string.ssp_android),
            context?.getString(R.string.ssp_android_url),
            context?.getString(R.string.sdp_android_license),
            true
        )
        val okhttp =
            LicenseModel(context?.getString(R.string.okhttp), context?.getString(R.string.okhttp_url), context?.getString(R.string.okhttp_license), false)
        val aosp = LicenseModel(context?.getString(R.string.aosp), context?.getString(R.string.aosp_url), context?.getString(R.string.aosp_license), false)
        val gson = LicenseModel(context?.getString(R.string.gson), context?.getString(R.string.gson_url), context?.getString(R.string.gson_license), false)
        val dexcount =
            LicenseModel(context?.getString(R.string.dexcount), context?.getString(R.string.dexcount_url), context?.getString(R.string.dexcount_license), false)

        listLibraries.add(aosp)
        listLibraries.add(googleDirection)
        listLibraries.add(dexcount)
        listLibraries.add(glide)
        listLibraries.add(gson)
        listLibraries.add(icons8)
        listLibraries.add(okhttp)
        listLibraries.add(retrofit)
        listLibraries.add(sdp)
        listLibraries.add(ssp)

        listData.value = listLibraries
    }

}