package com.yoesuv.infomadiun.menu.other.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.yoesuv.infomadiun.menu.other.views.ChildFragmentInfo

class TabOtherAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        if(position==1){
            return ChildFragmentInfo()
        }else{
            return ChildFragmentInfo()
        }
    }

    override fun getCount(): Int {
        return 4
    }
}