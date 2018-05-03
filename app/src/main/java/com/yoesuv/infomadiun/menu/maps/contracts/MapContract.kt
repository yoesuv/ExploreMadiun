package com.yoesuv.infomadiun.menu.maps.contracts

import com.yoesuv.infomadiun.menu.maps.models.PinModel

class MapContract {

    interface ViewMaps{
        fun showLoading()
        fun dismissLoading()
        fun setData(listPin: MutableList<PinModel>)
    }

    interface Presenter{
        fun getListPin()
        fun destroy()
    }
}