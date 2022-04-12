package com.kelaniya.android.locationsstore.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.kelaniya.android.locationsstore.R
import com.kelaniya.android.locationsstore.model.Location


class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var locationList = emptyList<Location>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
       return locationList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = locationList[position]
        holder.itemView.id_txt.text = currentItem.id.toString()
        holder.itemView.name.text = currentItem.name
        holder.itemView.latitude.text = currentItem.latitude
        holder.itemView.longitude.text = currentItem.longitude

        holder.itemView.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(location: List<Location>){
        this.locationList = location
        notifyDataSetChanged()
    }
}