package com.yoesuv.infomadiun.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.menu.gallery.views.FragmentGallery
import com.yoesuv.infomadiun.menu.listplace.views.FragmentListPlace
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

        supportFragmentManager.beginTransaction().replace(R.id.container, FragmentListPlace.getInstance()).commit()
    }

    private fun setupNavigationMenu(){
        bottomNavigationView.itemIconTintList = null
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView)
        
        bottomNavigationView.setOnNavigationItemSelectedListener({
            if (it.itemId==R.id.menuList) {
                supportFragmentManager.beginTransaction().replace(R.id.container, FragmentListPlace.getInstance()).commit()
            } else if(it.itemId==R.id.menuGallery) {
                supportFragmentManager.beginTransaction().replace(R.id.container, FragmentGallery.getInstance()).commit()
            }
            return@setOnNavigationItemSelectedListener true
        })
    }

}