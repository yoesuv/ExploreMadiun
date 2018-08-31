package com.yoesuv.infomadiun.menu.other.views

import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.databinding.FragmentOtherBinding
import com.yoesuv.infomadiun.menu.other.adapters.TabOtherAdapter
import com.yoesuv.infomadiun.utils.ZoomOutPageTransformer
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_other.view.*

/**
 *  Created by yusuf on 4/30/18.
 */
class FragmentOther: Fragment() {

    companion object {
        fun getInstance():Fragment{
            return FragmentOther()
        }
    }

    private lateinit var binding: FragmentOtherBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_other, container, false)

        binding.viewPagerOther.adapter = TabOtherAdapter(childFragmentManager)
        binding.viewPagerOther.setPageTransformer(true, ZoomOutPageTransformer())

        binding.navigationTabStrip.setViewPager(binding.viewPagerOther)
        binding.navigationTabStrip.setTitles(getString(R.string.informasi), getString(R.string.changelog), getString(R.string.thanks_to), getString(R.string.library))
        binding.navigationTabStrip.inactiveColor = ContextCompat.getColor(context!!, R.color.grey_50)
        binding.navigationTabStrip.activeColor = ContextCompat.getColor(context!!, R.color.white)
        binding.navigationTabStrip.stripColor = ContextCompat.getColor(context!!, R.color.colorAccent)
        binding.navigationTabStrip.titleSize = 25F
        binding.navigationTabStrip.cornersRadius = 0F

        return binding.root
    }
}