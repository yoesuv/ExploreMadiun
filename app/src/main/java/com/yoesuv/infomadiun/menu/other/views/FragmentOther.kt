package com.yoesuv.infomadiun.menu.other.views

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import com.google.android.material.appbar.AppBarLayout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.databinding.FragmentOtherBinding
import com.yoesuv.infomadiun.menu.other.adapters.TabOtherAdapter
import com.yoesuv.infomadiun.utils.ZoomOutPageTransformer
import com.yoesuv.infomadiun.utils.lollipopOrNewer

/**
 *  Updated by yusuf on 12 July 2020.
 */
class FragmentOther: Fragment() {

    private lateinit var binding: FragmentOtherBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_other, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPagerOther.adapter = TabOtherAdapter(context, childFragmentManager)
        binding.viewPagerOther.setPageTransformer(true, ZoomOutPageTransformer())
        binding.tabLayoutViewPagerOther.setupWithViewPager(binding.viewPagerOther)
        setupAppBar(0F)
    }

    override fun onDestroy() {
        super.onDestroy()
        setupAppBar(8F)
    }

    private fun setupAppBar(elevation: Float){
        lollipopOrNewer {
            activity?.findViewById<AppBarLayout>(R.id.mainAppBar)?.elevation = elevation
        }
    }
}