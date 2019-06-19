package com.yoesuv.infomadiun.menu.listplace.views

import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.databinding.ActivityDetailListplaceBinding
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel
import com.yoesuv.infomadiun.menu.listplace.viewmodels.CustomDetailListPlaceViewModelFactory
import com.yoesuv.infomadiun.menu.listplace.viewmodels.DetailListPlaceViewModel

class DetailListPlaceActivity: AppCompatActivity() {

    companion object {
        const val EXTRA_DATA_LIST_PLACE = "extra_data_list_place"
    }

    private lateinit var binding: ActivityDetailListplaceBinding
    private lateinit var viewModel: DetailListPlaceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.scale_down)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_listplace)
        val placeModel: PlaceModel = intent.getParcelableExtra(EXTRA_DATA_LIST_PLACE)!!
        viewModel = ViewModelProviders.of(this, CustomDetailListPlaceViewModelFactory(placeModel, application)).get(DetailListPlaceViewModel::class.java)
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
        overridePendingTransition(R.anim.scale_up, R.anim.slide_out_bottom)
    }

    private fun setupToolbar(){
        setSupportActionBar(binding.toolbarDetailListPlace.toolbarInclude)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbarDetailListPlace.textViewToolbarInclude.text = getString(R.string.detail_list_place)
    }

}