package com.yoesuv.infomadiun.menu.listplace.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableField
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel
import com.yoesuv.infomadiun.utils.AppHelper

class DetailListPlaceViewModel(placeModel: PlaceModel, application: Application) : AndroidViewModel(application) {

    var imageUrl: ObservableField<String> = ObservableField(placeModel.image!!)
    var title: ObservableField<String> = ObservableField(placeModel.name!!)
    var description: ObservableField<String> = ObservableField(AppHelper.fromHtml(placeModel.description!!))

}