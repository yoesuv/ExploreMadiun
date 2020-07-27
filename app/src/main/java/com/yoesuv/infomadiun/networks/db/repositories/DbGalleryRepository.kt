package com.yoesuv.infomadiun.networks.db.repositories

import android.content.Context
import android.os.AsyncTask
import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel
import com.yoesuv.infomadiun.networks.db.AppDatabase

class DbGalleryRepository(context: Context) {

    private val dbGallery = AppDatabase.getInstance(context)?.galleryDaoAccess()

    fun insertGallery(galleryModel: GalleryModel) {
        AsyncTask.execute { dbGallery?.insertGallery(galleryModel) }
    }

    fun galleries(galleries:(List<GalleryModel>?) -> Unit) {
        AsyncTask.execute { galleries(dbGallery?.galleries()) }
    }

    fun deleteAllGallery() {
        AsyncTask.execute { dbGallery?.deleteAllGallery() }
    }

}