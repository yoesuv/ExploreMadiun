package com.yoesuv.infomadiun.main

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.WindowManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.databinding.ActivityMainBinding
import com.yoesuv.infomadiun.utils.AppHelper

/**
 *  Updated by yusuf on 24 July 2020.
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
        binding.lifecycleOwner = this

        setupToolbar()
        setupNavigation()
    }

    override fun onBackPressed() {
        if (BACK_PRESSED+2000L>System.currentTimeMillis()) {
            super.onBackPressed()
        } else {
            AppHelper.displayNormalToast(this,getString(R.string.press_again_to_exit))
        }
        BACK_PRESSED = System.currentTimeMillis()
    }

    private fun setupToolbar(){
        setSupportActionBar(binding.toolbarMain)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setupNavigation() {
        binding.bottomNavigationView.itemIconTintList = null
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val navController = navHostFragment.findNavController()
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
    }

}