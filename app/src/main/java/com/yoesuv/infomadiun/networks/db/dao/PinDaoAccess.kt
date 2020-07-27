package com.yoesuv.infomadiun.networks.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yoesuv.infomadiun.menu.maps.models.PinModel

@Dao
interface PinDaoAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPin(pinModel: PinModel)

    @Query("SELECT * FROM pins")
    fun pins(): List<PinModel>

    @Query("DELETE FROM pins")
    fun deleteAllPin()

}