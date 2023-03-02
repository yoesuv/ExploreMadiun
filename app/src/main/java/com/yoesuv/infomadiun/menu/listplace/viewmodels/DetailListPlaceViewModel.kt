package com.yoesuv.infomadiun.menu.listplace.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel
import com.yoesuv.infomadiun.utils.AppHelper

class DetailListPlaceViewModel(placeModel: PlaceModel) : ViewModel() {

    var imageUrl: ObservableField<String> = ObservableField(placeModel.image)
    var title: ObservableField<String> = ObservableField(placeModel.name)
    var description: ObservableField<String> = ObservableField(AppHelper.fromHtml(placeModel.description))

}