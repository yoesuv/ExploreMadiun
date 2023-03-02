package com.yoesuv.infomadiun.menu.other.views

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import com.google.android.material.appbar.AppBarLayout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.databinding.FragmentOtherBinding
import com.yoesuv.infomadiun.menu.other.adapters.TabOtherAdapter
import com.yoesuv.infomadiun.utils.ZoomOutPageTransformer

/**
 *  Updated by yusuf on 06 June 2021.
 */
class FragmentOther : Fragment() {

    private lateinit var binding: FragmentOtherBinding
    private var tabTitles = arrayOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tabTitles = resources.getStringArray(R.array.tab_other)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_other, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPagerOtherTwo.adapter = TabOtherAdapter(this, tabTitles)
        binding.viewPagerOtherTwo.setPageTransformer(ZoomOutPageTransformer())
        setupAppBar(0F)
        setupTab()
    }

    override fun onDestroy() {
        super.onDestroy()
        setupAppBar(8F)
    }

    private fun setupAppBar(elevation: Float) {
        activity?.findViewById<AppBarLayout>(R.id.mainAppBar)?.elevation = elevation
    }

    private fun setupTab() {
        TabLayoutMediator(binding.tabLayoutViewPagerOtherTwo, binding.viewPagerOtherTwo) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }
}