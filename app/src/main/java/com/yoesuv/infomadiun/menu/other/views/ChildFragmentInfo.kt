package com.yoesuv.infomadiun.menu.other.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yoesuv.infomadiun.BuildConfig
import com.yoesuv.infomadiun.R
import kotlinx.android.synthetic.main.child_fragment_info.view.*

class ChildFragmentInfo: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = LayoutInflater.from(context).inflate(R.layout.child_fragment_info, container, false)

        view.imageViewAppInfo.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.ic_app_info))
        val version = resources.getString(R.string.info_app_version, BuildConfig.VERSION_NAME)
        view.textViewVersion.text = version

        return view
    }

}