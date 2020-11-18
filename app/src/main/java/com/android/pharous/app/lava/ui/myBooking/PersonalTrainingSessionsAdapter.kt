package com.android.pharous.app.lava.ui.myBooking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.ui.training.models.SessionResponse
import kotlinx.android.synthetic.main.my_bookin_item.view.*

class PersonalTrainingSessionsAdapter :
    ListAdapter<SessionResponse,PersonalTrainingSessionsAdapter.ViewHolder>(DiffCallback) {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item : SessionResponse){

            itemView.apply {
                nameTV.text = item.serviceName
                durationTV.text = "Duration :  ${item.time}"
                dateTV.text = "Date : ${item.date}"
            }

        }

    }

    object DiffCallback : DiffUtil.ItemCallback<SessionResponse>() {
        override fun areItemsTheSame(oldItem: SessionResponse, newItem: SessionResponse): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: SessionResponse, newItem: SessionResponse): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.my_bookin_item, parent, false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(getItem(position))
    }
}