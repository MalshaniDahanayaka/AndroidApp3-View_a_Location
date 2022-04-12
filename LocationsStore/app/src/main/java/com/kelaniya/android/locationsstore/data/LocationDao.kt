package com.kelaniya.android.locationsstore.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kelaniya.android.locationsstore.model.Location


@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLocation(location: Location)

    @Update
    suspend fun updateLocation(location: Location)

    @Delete
    suspend fun deleteLocation(location: Location)

    @Query("DELETE FROM location_table")
    suspend fun deleteAllLocations()

    @Query("SELECT * FROM location_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Location>>

}