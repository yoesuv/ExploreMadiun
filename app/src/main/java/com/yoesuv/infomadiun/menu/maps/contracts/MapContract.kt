package com.yoesuv.infomadiun.menu.maps.contracts

import com.yoesuv.infomadiun.menu.maps.models.PinModel

class MapContract {

    interface ViewMaps{
        fun showLoading()
        fun dismissLoading()
        fun setData(listPin: MutableList<PinModel>)
        fun setError()
    }

    interface Presenter{
        fun getListPin()
        fun destroy()
    }
}