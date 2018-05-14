package com.yoesuv.infomadiun.menu.other.views

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

    private lateinit var appBarLayout: AppBarLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_other, container, false)

        val activity: AppCompatActivity = activity as AppCompatActivity
        appBarLayout = activity.findViewById(R.id.mainAppBar)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appBarLayout.elevation = 0f
            view.navigationTabStrip.elevation = 5f
        }
        val toolbar = activity.findViewById<Toolbar>(R.id.toolbarMain)
        toolbar.textViewToolbar.text = getString(R.string.menu_other)

        view.viewPagerOther.adapter = TabOtherAdapter(childFragmentManager)
        view.viewPagerOther.setPageTransformer(true, ZoomOutPageTransformer())

        view.navigationTabStrip.setViewPager(view.viewPagerOther)
        view.navigationTabStrip.setTitles(getString(R.string.informasi), getString(R.string.changelog), getString(R.string.thanks_to), getString(R.string.library))
        view.navigationTabStrip.inactiveColor = ContextCompat.getColor(context!!, R.color.grey_50)
        view.navigationTabStrip.activeColor = ContextCompat.getColor(context!!, R.color.white)
        view.navigationTabStrip.stripColor = ContextCompat.getColor(context!!, R.color.colorAccent)
        view.navigationTabStrip.titleSize = 25f
        view.navigationTabStrip.cornersRadius = 0f

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appBarLayout.elevation = 8f
        }
    }
}