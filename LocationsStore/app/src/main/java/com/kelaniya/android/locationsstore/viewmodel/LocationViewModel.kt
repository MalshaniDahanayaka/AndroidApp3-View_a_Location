package com.kelaniya.android.locationsstore.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.kelaniya.android.locationsstore.data.LocationDatabase
import com.kelaniya.android.locationsstore.model.Location
import com.kelaniya.android.locationsstore.repository.LocationRepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Location>>
    private val repository: LocationRepository

    init {
        val locationDao = LocationDatabase.getDatabase(
            application
        ).locationDao()
        repository = LocationRepository(locationDao)
        readAllData = repository.readAllData
    }

    fun addLocation(location: Location){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addLocation(location)
        }
    }

    fun updateLocation(location: Location){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateLocation(location)
        }
    }

    fun deleteLocation(location: Location){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteLocation(location)
        }
    }

    fun deleteAllLocations(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllLocations()
        }
    }

}