package com.yoesuv.infomadiun.menu.listplace.contracts

import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel

/**
 *  Created by yusuf on 4/30/18.
 */
class ListPlaceContract {

    interface ViewListPlace{
        fun showLoading()
        fun dismissLoading()
        fun setData(listPlaceModel: MutableList<PlaceModel>)

    }

    interface Presenter{
        fun getListPlace()
        fun destroy()
    }

}