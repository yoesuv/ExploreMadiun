package com.yoesuv.infomadiun.utils

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View

/**
 *  Created by yusuf on 5/1/18.
 */

class BottomNavigationViewBehavior(context: Context, attributeSet: AttributeSet) : CoordinatorLayout.Behavior<BottomNavigationView>(context, attributeSet) {

    private val height: Int = AppHelper.getToolbarHeight(context)

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: BottomNavigationView?, dependency: View?): Boolean {
        return dependency is AppBarLayout
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout?, child: BottomNavigationView?, dependency: View?): Boolean {
        if (dependency is AppBarLayout) {
            val lp = child!!.layoutParams as CoordinatorLayout.LayoutParams
            val bottomMargin = lp.bottomMargin
            val distanceToScroll = child.height + bottomMargin
            val ratio = dependency.y / height
            child.translationY = -distanceToScroll * ratio
        }
        return true
    }
}