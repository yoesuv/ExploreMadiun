package com.yoesuv.infomadiun.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.menu.gallery.views.FragmentGallery
import com.yoesuv.infomadiun.menu.listplace.views.FragmentListPlace
import com.yoesuv.infomadiun.menu.maps.views.FragmentMaps
import com.yoesuv.infomadiun.menu.other.views.FragmentOther
import com.yoesuv.infomadiun.utils.BottomNavigationViewHelper
import kotlinx.android.synthetic.main.activity_main.*

/**
 *  Created by yusuf on 4/28/18.
 */
class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavigationMenu()
        setupToolbar()

        supportFragmentManager.beginTransaction().replace(R.id.container, FragmentListPlace.getInstance()).commit()
    }

    private fun setupNavigationMenu(){
        bottomNavigationView.itemIconTintList = null
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView)
        
        bottomNavigationView.setOnNavigationItemSelectedListener({
            when {
                it.itemId==R.id.menuList -> supportFragmentManager.beginTransaction().replace(R.id.container, FragmentListPlace.getInstance()).commit()
                it.itemId==R.id.menuGallery -> supportFragmentManager.beginTransaction().replace(R.id.container, FragmentGallery.getInstance()).commit()
                it.itemId==R.id.menuMap -> supportFragmentManager.beginTransaction().replace(R.id.container, FragmentMaps.getInstance()).commit()
                it.itemId==R.id.menuOther -> supportFragmentManager.beginTransaction().replace(R.id.container, FragmentOther.getInstance()).commit()
            }

            return@setOnNavigationItemSelectedListener true
        })
    }

    private fun setupToolbar(){
        setSupportActionBar(toolbarMain)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

}