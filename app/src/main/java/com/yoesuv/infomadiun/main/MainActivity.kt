package com.yoesuv.infomadiun.main

import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.WindowManager
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.databinding.ActivityMainBinding
import com.yoesuv.infomadiun.menu.gallery.views.FragmentGallery
import com.yoesuv.infomadiun.menu.listplace.views.FragmentListPlace
import com.yoesuv.infomadiun.menu.maps.views.FragmentMaps
import com.yoesuv.infomadiun.menu.other.views.FragmentOther
import com.yoesuv.infomadiun.utils.AppHelper
import com.yoesuv.infomadiun.utils.BottomNavigationViewHelper

/**
 *  Created by yusuf on 4/28/18.
 */
class MainActivity: AppCompatActivity() {

    companion object {
        var BACK_PRESSED: Long = 0L
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupNavigationMenu()
        setupToolbar()

        supportFragmentManager.beginTransaction().replace(R.id.container, FragmentListPlace.getInstance()).commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        for(fragment in supportFragmentManager.fragments){
            fragment.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onBackPressed() {
        if (BACK_PRESSED+2000L>System.currentTimeMillis()) {
            super.onBackPressed()
        } else {
            AppHelper.displayNormalToast(this,getString(R.string.press_again_to_exit))
        }
        BACK_PRESSED = System.currentTimeMillis()
    }

    private fun setupNavigationMenu(){
        binding.bottomNavigationView.itemIconTintList = null
        BottomNavigationViewHelper.disableShiftMode(binding.bottomNavigationView)
        
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when {
                it.itemId==R.id.menuList -> supportFragmentManager.beginTransaction().replace(R.id.container, FragmentListPlace.getInstance()).commit()
                it.itemId==R.id.menuGallery -> supportFragmentManager.beginTransaction().replace(R.id.container, FragmentGallery.getInstance()).commit()
                it.itemId==R.id.menuMap -> supportFragmentManager.beginTransaction().replace(R.id.container, FragmentMaps.getInstance()).commit()
                it.itemId==R.id.menuOther -> supportFragmentManager.beginTransaction().replace(R.id.container, FragmentOther.getInstance()).commit()
            }

            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun setupToolbar(){
        setSupportActionBar(binding.toolbarMain)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

}