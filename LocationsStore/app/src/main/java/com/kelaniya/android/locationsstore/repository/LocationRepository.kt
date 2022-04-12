package com.kelaniya.android.locationsstore.repository

import androidx.lifecycle.LiveData
import com.kelaniya.android.locationsstore.data.LocationDao
import com.kelaniya.android.locationsstore.model.Location


class LocationRepository(private val locationDao: LocationDao) {

    val readAllData: LiveData<List<Location>> = locationDao.readAllData()

    suspend fun addLocation(location: Location){
        locationDao.addLocation(location)
    }

    suspend fun updateLocation(location: Location){
        locationDao.updateLocation(location)
    }

    suspend fun deleteLocation(location: Location){
        locationDao.deleteLocation(location)
    }

    suspend fun deleteAllLocations(){
        locationDao.deleteAllLocations()
    }

}