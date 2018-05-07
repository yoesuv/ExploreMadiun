package com.yoesuv.infomadiun.menu.other.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.yoesuv.infomadiun.menu.other.views.ChildFragmentChangelog
import com.yoesuv.infomadiun.menu.other.views.ChildFragmentInfo

class TabOtherAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ChildFragmentInfo()
            1 -> ChildFragmentChangelog()
            else -> ChildFragmentInfo()
        }
    }

    override fun getCount(): Int {
        return 4
    }
}