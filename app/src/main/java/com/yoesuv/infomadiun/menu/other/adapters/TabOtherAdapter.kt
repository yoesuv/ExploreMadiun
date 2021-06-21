package com.yoesuv.infomadiun.menu.other.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yoesuv.infomadiun.menu.other.views.ChildFragmentChangelog
import com.yoesuv.infomadiun.menu.other.views.ChildFragmentInfo
import com.yoesuv.infomadiun.menu.other.views.ChildFragmentLibraries
import com.yoesuv.infomadiun.menu.other.views.ChildFragmentThanks

class TabOtherAdapter(fm: Fragment, private val titles: Array<String>) : FragmentStateAdapter(fm) {

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ChildFragmentInfo.getInstance()
            1 -> ChildFragmentChangelog.getInstance()
            2 -> ChildFragmentThanks.getInstance()
            3 -> ChildFragmentLibraries.getInstance()
            else -> ChildFragmentInfo.getInstance()
        }
    }
}