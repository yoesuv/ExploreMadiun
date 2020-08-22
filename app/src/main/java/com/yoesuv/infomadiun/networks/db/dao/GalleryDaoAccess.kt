package com.yoesuv.infomadiun.networks.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel

@Dao
interface GalleryDaoAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGallery(galleryModel: GalleryModel)

    @Query("SELECT * FROM galleries")
    suspend fun galleries(): List<GalleryModel>

    @Query("DELETE FROM galleries")
    suspend fun deleteAllGallery()

}