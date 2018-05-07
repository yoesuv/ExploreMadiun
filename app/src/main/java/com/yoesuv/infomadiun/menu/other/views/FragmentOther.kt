package com.yoesuv.infomadiun.menu.other.views

import android.os.Build
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gigamole.navigationtabstrip.NavigationTabStrip
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.menu.other.adapters.TabOtherAdapter
import com.yoesuv.infomadiun.utils.ZoomOutPageTransformer
import kotlinx.android.synthetic.main.activity_main.view.*

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
        }
        val toolbar = activity.findViewById<Toolbar>(R.id.toolbarMain)
        toolbar.textViewToolbar.text = getString(R.string.menu_other)

        val tab:NavigationTabStrip = view.findViewById(R.id.navigationTabStrip)
        val viewPager: ViewPager = view.findViewById(R.id.viewPagerOther)

        viewPager.adapter = TabOtherAdapter(childFragmentManager)
        viewPager.setPageTransformer(true, ZoomOutPageTransformer())
        tab.setViewPager(viewPager)
        tab.setTitles(getString(R.string.informasi), getString(R.string.changelog), getString(R.string.thanks_to), getString(R.string.library))
        tab.inactiveColor = ContextCompat.getColor(context!!, R.color.grey_50)
        tab.activeColor = ContextCompat.getColor(context!!, R.color.white)
        tab.stripColor = ContextCompat.getColor(context!!, R.color.colorAccent)
        tab.titleSize = 25f
        tab.cornersRadius = 0f

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appBarLayout.elevation = 8f
        }
    }
}