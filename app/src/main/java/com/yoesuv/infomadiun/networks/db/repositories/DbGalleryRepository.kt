package com.yoesuv.infomadiun.networks.db.repositories

import android.content.Context
import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel
import com.yoesuv.infomadiun.networks.db.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DbGalleryRepository(context: Context, private val scope: CoroutineScope) {

    private val dbGallery = AppDatabase.getInstance(context)?.galleryDaoAccess()

    fun galleries(galleries: (List<GalleryModel>?) -> Unit) {
        scope.launch {
            galleries(dbGallery?.galleries())
        }
    }

    suspend fun setupDataGalleries(galleries: (List<GalleryModel>?)) {
        dbGallery?.deleteAllGallery()
        galleries?.forEach { galleryModel ->
            dbGallery?.insertGallery(galleryModel)
        }
    }

}