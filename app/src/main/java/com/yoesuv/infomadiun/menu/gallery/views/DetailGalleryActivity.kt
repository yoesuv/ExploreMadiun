package com.yoesuv.infomadiun.menu.gallery.views

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.databinding.ActivityDetailGalleryBinding
import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel
import com.yoesuv.infomadiun.menu.gallery.viewmodels.CustomDetailGalleryViewModelFactory
import com.yoesuv.infomadiun.menu.gallery.viewmodels.DetailGalleryViewModel
import com.yoesuv.infomadiun.utils.nougatOrBelow

class DetailGalleryActivity: AppCompatActivity() {

    companion object {
        const val EXTRA_DATA_GALLERY = "extra_data_gallery"
    }

    private lateinit var binding: ActivityDetailGalleryBinding
    private lateinit var viewModel: DetailGalleryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nougatOrBelow {
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.scale_down)
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_gallery)
        binding.lifecycleOwner = this
        val galleryModel: GalleryModel = intent?.getParcelableExtra(EXTRA_DATA_GALLERY) as GalleryModel
        viewModel = ViewModelProvider(this, CustomDetailGalleryViewModelFactory(application, galleryModel)).get(DetailGalleryViewModel::class.java)
        binding.gallery = viewModel

        setupToolbar()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId==android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item!!)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        nougatOrBelow {
            overridePendingTransition(R.anim.scale_up, R.anim.slide_out_bottom)
        }
    }

    private fun setupToolbar(){
        setSupportActionBar(binding.toolbarDetailGallery.toolbarInclude)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbarDetailGallery.textViewToolbarInclude.text = getString(R.string.detail_gallery)
    }

}