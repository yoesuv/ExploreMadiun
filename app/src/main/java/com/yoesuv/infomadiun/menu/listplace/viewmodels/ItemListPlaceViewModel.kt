package com.yoesuv.infomadiun.menu.listplace.viewmodels

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.content.Intent
import android.databinding.ObservableField
import android.view.View
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel
import com.yoesuv.infomadiun.menu.listplace.views.DetailListPlaceActivity
import java.lang.ref.WeakReference

class ItemListPlaceViewModel(private val weakContext: WeakReference<Context>, private val placeModel: PlaceModel): ViewModel() {

    var name: ObservableField<String> = ObservableField(placeModel.name!!)
    var location: ObservableField<String> = ObservableField(placeModel.location!!)
    var imageUrl: ObservableField<String> = ObservableField(placeModel.image!!)

    fun clickItemListPlace(view: View){
        val intent = Intent(weakContext.get(), DetailListPlaceActivity::class.java)
        intent.putExtra(DetailListPlaceActivity.EXTRA_DATA_LIST_PLACE, placeModel)
        weakContext.get()?.startActivity(intent)
    }

}