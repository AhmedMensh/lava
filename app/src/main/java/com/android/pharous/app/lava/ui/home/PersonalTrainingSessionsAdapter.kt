package com.android.pharous.app.lava.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.common.IItemClickListener
import com.android.pharous.app.lava.models.ExerciseReservationResponse
import com.android.pharous.app.lava.ui.training.models.SessionResponse
import kotlinx.android.synthetic.main.upcoming_booking_item.view.*
import kotlinx.android.synthetic.main.workout_item.view.*

class PersonalTrainingSessionsAdapter(private val listener : IItemClickListener<Any>) :
    ListAdapter<SessionResponse,PersonalTrainingSessionsAdapter.ViewHolder>(DiffCallback) {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item : SessionResponse,listener: IItemClickListener<Any>){

            itemView.exerciseNameTV.text = item.serviceName
            itemView.exerciseDurationTv.text = item.time
           itemView.creationDateTV.text= item.date

            itemView.setOnClickListener { listener.onItemClick(item) }
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
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.upcoming_booking_item, parent, false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(getItem(position),listener)
    }
}