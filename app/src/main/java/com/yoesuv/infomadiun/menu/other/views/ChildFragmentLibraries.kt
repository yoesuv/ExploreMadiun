package com.yoesuv.infomadiun.menu.other.views

import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.utils.AppHelper
import kotlinx.android.synthetic.main.child_fragment_libraries.view.*

class ChildFragmentLibraries: Fragment() {

    companion object {
        fun getInstance(): Fragment{
            return ChildFragmentLibraries()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = LayoutInflater.from(context).inflate(R.layout.child_fragment_libraries, container, false)

        view.textViewIcons8License.text = AppHelper.fromHtml(getString(R.string.icons8_license))
        view.textViewRetrofitLicense.text = AppHelper.fromHtml(getString(R.string.retrofit_license))
        view.textViewRxJavaLicense.text = AppHelper.fromHtml(getString(R.string.rxjava_license))
        view.textViewRxAndroidLicense.text = AppHelper.fromHtml(getString(R.string.rxAndroid_license))
        view.textViewRxKotlinLicense.text = AppHelper.fromHtml(getString(R.string.rx_kotlin_license))
        view.textViewGlideLicense.text = AppHelper.fromHtml(getString(R.string.glide_license))
        view.textViewPhotoViewLicense.text = AppHelper.fromHtml(getString(R.string.photoview_license))
        view.textViewRxPermissionLicense.text = AppHelper.fromHtml(getString(R.string.rx_permission_license))
        view.textViewToastyLicense.text = AppHelper.fromHtml(getString(R.string.toasty_license))
        view.textViewNavigationTabStripLicense.text = AppHelper.fromHtml(getString(R.string.navigation_tab_strip_license))

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            view.nestedScrollViewLibraries.isNestedScrollingEnabled = true
        }

        return view
    }

}