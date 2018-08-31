package com.yoesuv.infomadiun.menu.other.views

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yoesuv.infomadiun.BuildConfig
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.databinding.ChildFragmentInfoBinding

class ChildFragmentInfo: Fragment() {

    companion object {
        fun getInstance():Fragment{
            return ChildFragmentInfo()
        }
    }

    private lateinit var binding: ChildFragmentInfoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.child_fragment_info, container, false)

        val version = resources.getString(R.string.info_app_version, BuildConfig.VERSION_NAME)
        binding.textViewVersion.text = version

        return binding.root
    }

}