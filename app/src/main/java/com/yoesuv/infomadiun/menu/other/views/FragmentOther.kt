package com.yoesuv.infomadiun.menu.other.views

import androidx.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import com.google.android.material.appbar.AppBarLayout
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
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
class FragmentOther: androidx.fragment.app.Fragment() {

    companion object {
        fun getInstance(): androidx.fragment.app.Fragment {
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

        setupAppBar(0F)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        setupAppBar(8F)
    }

    private fun setupAppBar(elevation: Float){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            activity?.findViewById<AppBarLayout>(R.id.mainAppBar)?.elevation = elevation
        }
    }
}