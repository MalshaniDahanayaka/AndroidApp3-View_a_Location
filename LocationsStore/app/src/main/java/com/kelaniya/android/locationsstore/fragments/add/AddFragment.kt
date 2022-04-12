package com.kelaniya.android.locationsstore.fragments.add

import android.location.Location
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kelaniya.android.locationsstore.R
import com.kelaniya.android.locationsstore.viewmodel.LocationViewModel


class AddFragment : Fragment() {

    private lateinit var mLocationViewModel: LocationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mLocationViewModel = ViewModelProvider(this).get(LocationViewModel::class.java)

        view.add_btn.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val name = addLocation_name.text.toString()
        val latitude = addLatitude_value.text.toString()
        val longitude = addLongitude_value.text

        if(inputCheck(name, latitude, longitude)){
            // Create User Object
            val location = Location(
                0,
                name,
                latitude,
                longitude
            )
            // Add Data to Database
            mLocationViewModel.addLocation(location)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name: String, latitude: String, longitude: String): Boolean{
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(latitude) && TextUtils.isEmpty(longitude))
    }

}