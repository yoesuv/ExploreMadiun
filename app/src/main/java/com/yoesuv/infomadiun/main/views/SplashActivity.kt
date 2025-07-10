package com.yoesuv.infomadiun.main.views

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.databinding.ActivitySplashBinding
import com.yoesuv.infomadiun.main.viewmodels.SplashViewModel
import com.yoesuv.infomadiun.utils.AppHelper

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (AppHelper.isVanillaIceCreamAndUp()) {
            enableEdgeToEdge()
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        viewModel = ViewModelProvider(this)[SplashViewModel::class.java]
        binding.splash = viewModel

        viewModel.getAppData(this)
    }

}