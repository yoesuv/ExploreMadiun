package com.yoesuv.infomadiun.networks.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel

@Dao
interface PlaceDaoAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlace(placeModel: PlaceModel)

    @Query("SELECT * FROM PLACES")
    fun places(): List<PlaceModel>

    @Query("DELETE FROM places")
    fun deleteAllPlace()
}