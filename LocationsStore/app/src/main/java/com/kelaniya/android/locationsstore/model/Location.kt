package com.kelaniya.android.locationsstore.model


import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "location_table")
data class Location(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val latitude: String,
    val longitude: String
)