package com.yoesuv.infomadiun.menu.gallery.contracts

import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel

/**
 *  Created by yusuf on 5/1/18.
 */
class GalleryContract {

    interface ViewGallery{
        fun showLoading()
        fun dismissLoading()
        fun setData(listGallery: MutableList<GalleryModel>)
        fun setError()
    }

    interface Presenter{
        fun getListGallery()
        fun destroy()
    }
}