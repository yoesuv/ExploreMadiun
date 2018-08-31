package com.yoesuv.infomadiun.menu.listplace.viewmodels

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel

class ItemListPlaceViewModel(placeModel: PlaceModel): ViewModel() {

    var name: ObservableField<String> = ObservableField(placeModel.name!!)
    var location: ObservableField<String> = ObservableField(placeModel.location!!)
    var imageUrl: ObservableField<String> = ObservableField(placeModel.image!!)

}