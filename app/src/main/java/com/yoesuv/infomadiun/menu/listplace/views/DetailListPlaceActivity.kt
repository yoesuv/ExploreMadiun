package com.yoesuv.infomadiun.menu.listplace.views

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.databinding.ActivityDetailListplaceBinding
import com.yoesuv.infomadiun.menu.listplace.viewmodels.DetailListPlaceViewModel

class DetailListPlaceActivity: AppCompatActivity() {

    companion object {
        const val EXTRA_DATA_LIST_PLACE = "extra_data_list_place"
    }

    private lateinit var binding: ActivityDetailListplaceBinding
    private lateinit var viewModel: DetailListPlaceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_listplace)
    }

}