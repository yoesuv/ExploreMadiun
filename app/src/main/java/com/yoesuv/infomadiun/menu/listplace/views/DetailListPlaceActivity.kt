package com.yoesuv.infomadiun.menu.listplace.views

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.databinding.ActivityDetailListplaceBinding
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel
import com.yoesuv.infomadiun.menu.listplace.viewmodels.CustomDetailListPlaceViewModelFactory
import com.yoesuv.infomadiun.menu.listplace.viewmodels.DetailListPlaceViewModel
import com.yoesuv.infomadiun.utils.nougatOrBelow

class DetailListPlaceActivity: AppCompatActivity() {

    companion object {
        const val EXTRA_DATA_LIST_PLACE = "extra_data_list_place"
    }

    private lateinit var binding: ActivityDetailListplaceBinding
    private lateinit var viewModel: DetailListPlaceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nougatOrBelow {
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.scale_down)
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_listplace)
        binding.lifecycleOwner = this
        val placeModel: PlaceModel = intent.getParcelableExtra(EXTRA_DATA_LIST_PLACE)!!
        viewModel = ViewModelProvider(this, CustomDetailListPlaceViewModelFactory(application, placeModel)).get(DetailListPlaceViewModel::class.java)
        binding.listPlace = viewModel

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
        setSupportActionBar(binding.toolbarDetailListPlace.toolbarInclude)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbarDetailListPlace.textViewToolbarInclude.text = getString(R.string.detail_list_place)
    }

}