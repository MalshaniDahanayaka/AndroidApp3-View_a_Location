package com.kelaniya.android.locationsstore.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kelaniya.android.locationsstore.R
import com.kelaniya.android.locationsstore.model.Location
import com.kelaniya.android.locationsstore.viewmodel.LocationViewModel


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mLocationViewModel: LocationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mLocationViewModel = ViewModelProvider(this).get(LocationViewModel::class.java)

        view.update_name.setText(args.currentUser.name)
        view.updateLatitude.setText(args.currentUser.latitude)
        view.updateLongitude.setText(args.currentUser.longitude)

        view.update_btn.setOnClickListener {
            updateItem()
        }

        // Add menu
        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem() {
        val name = update_name.text.toString()
        val latitude = updateLatitude.text.toString()
        val longitude = updateLongitude.text.toString()

        if (inputCheck(name, latitude, longitude) ){
            // Create User Object
            val updatedLocation = Location(args.currentUser.id, name, latitude, longitude)
            // Update Current User
            mLocationViewModel.updateLocation(updatedLocation)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            // Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheck(name: String, latitude: String, longitude: String): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(latitude) && TextUtils.isEmpty(longitude))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteLocation()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteLocation() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mLocationViewModel.deleteLocation(args.currentLocation)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentLocation.name}",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentLocation.name}?")
        builder.setMessage("Are you sure you want to delete ${args.currentLocation.name}?")
        builder.create().show()
    }
}