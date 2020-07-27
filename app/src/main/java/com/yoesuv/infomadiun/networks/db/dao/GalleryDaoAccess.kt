package com.yoesuv.infomadiun.networks.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel

@Dao
interface GalleryDaoAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGallery(galleryModel: GalleryModel)

    @Query("SELECT * FROM galleries")
    fun galleries(): List<GalleryModel>

    @Query("DELETE FROM galleries")
    fun deleteAllGallery()

}