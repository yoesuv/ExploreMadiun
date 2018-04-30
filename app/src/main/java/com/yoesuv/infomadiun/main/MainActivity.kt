package com.yoesuv.infomadiun.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yoesuv.infomadiun.R
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
    }

    private fun setupNavigationMenu(){
        bottomNavigationView.itemIconTintList = null
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView)
    }

}