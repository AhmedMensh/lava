package com.android.pharous.app.lava.ui.membership

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.models.Service
import kotlinx.android.synthetic.main.membership_service_item.view.*

class MembershipServicesAdapter :
    ListAdapter<Service,MembershipServicesAdapter.ViewHolder>(DiffCallback) {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(service : Service){
            itemView.serviceNameTV.text = service.serviceName
        }

    }

    object DiffCallback : DiffUtil.ItemCallback<Service>() {
        override fun areItemsTheSame(oldItem: Service, newItem: Service): Boolean {
            return oldItem.serviceID == newItem.serviceID
        }

        override fun areContentsTheSame(oldItem: Service, newItem: Service): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.membership_service_item, parent, false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(getItem(position))
    }
}