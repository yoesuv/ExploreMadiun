package com.yoesuv.infomadiun.menu.other.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.menu.other.views.ChildFragmentChangelog
import com.yoesuv.infomadiun.menu.other.views.ChildFragmentInfo
import com.yoesuv.infomadiun.menu.other.views.ChildFragmentLibraries
import com.yoesuv.infomadiun.menu.other.views.ChildFragmentThanks

class TabOtherAdapter(context: Context?, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val arrayTab = context?.resources?.getStringArray(R.array.tab_other)

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ChildFragmentInfo.getInstance()
            1 -> ChildFragmentChangelog.getInstance()
            2 -> ChildFragmentThanks.getInstance()
            3 -> ChildFragmentLibraries.getInstance()
            else -> ChildFragmentInfo.getInstance()
        }
    }

    override fun getCount(): Int {
        return arrayTab?.size!!
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return arrayTab?.get(position)
    }
}