package com.yoesuv.infomadiun.main

import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.ViewGroupCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.doOnLayout
import androidx.core.view.insets.ColorProtection
import androidx.core.view.updateLayoutParams
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.databinding.ActivityMainBinding
import com.yoesuv.infomadiun.utils.AppHelper

/**
 *  Updated by yusuf on 02 March 2023.
 */
class MainActivity : AppCompatActivity() {
    companion object {
        var backPressed: Long = 0L
    }

    private lateinit var binding: ActivityMainBinding
    private var bottomSystemInset = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT))

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        setupToolbar()
        setupNavigation()
        setupBackPressed()

        ViewGroupCompat.installCompatInsetsDispatch(binding.mainProtectionLayout)
        AppHelper.applySystemBarInsets(
            binding.mainAppBar,
            top = true,
        )
        AppHelper.applySystemBarInsets(
            binding.bottomNavigationView,
            bottom = true,
        )
        ViewCompat.setOnApplyWindowInsetsListener(binding.container) { view, windowInsets ->
            bottomSystemInset =
                windowInsets
                    .getInsets(
                        WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout(),
                    ).bottom
            view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                bottomMargin = contentBottomMargin()
            }
            windowInsets
        }
        binding.bottomNavigationView.doOnLayout {
            (binding.bottomNavigationView.layoutParams as? CoordinatorLayout.LayoutParams)
                ?.behavior
                ?.let { it as? HideBottomViewOnScrollBehavior<*> }
                ?.addOnScrollStateChangedListener { _, _ ->
                    binding.container.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                        bottomMargin = contentBottomMargin()
                    }
                }
            ViewCompat.requestApplyInsets(binding.container)
        }

        binding.mainProtectionLayout.setProtections(
            listOf(
                ColorProtection(
                    WindowInsetsCompat.Side.TOP,
                    getColor(R.color.colorPrimary),
                ),
            ),
        )
    }

    private fun contentBottomMargin(): Int = if (isBottomNavigationHidden()) bottomSystemInset else binding.bottomNavigationView.height

    private fun isBottomNavigationHidden(): Boolean =
        (binding.bottomNavigationView.layoutParams as? CoordinatorLayout.LayoutParams)
            ?.behavior
            ?.let { it as? HideBottomViewOnScrollBehavior<*> }
            ?.isScrolledDown == true

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbarMain)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setupNavigation() {
        binding.bottomNavigationView.itemIconTintList = null
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val navController = navHostFragment.findNavController()
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
    }

    private fun setupBackPressed() {
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (backPressed + 2000L > System.currentTimeMillis()) {
                        finish()
                    } else {
                        @StringRes val msg = R.string.press_again_to_exit
                        AppHelper.displayNormalToast(this@MainActivity, msg)
                    }
                    backPressed = System.currentTimeMillis()
                }
            },
        )
    }
}
