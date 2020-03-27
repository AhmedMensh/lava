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
import kotlinx.android.synthetic.main.upcoming_booking_item.view.*
import kotlinx.android.synthetic.main.workout_item.view.*

class UpcomingBookingsAdapter(private val listener : IItemClickListener<ExerciseReservationResponse>) :
    ListAdapter<ExerciseReservationResponse,UpcomingBookingsAdapter.ViewHolder>(DiffCallback) {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(exerciseReservation : ExerciseReservationResponse,listener: IItemClickListener<ExerciseReservationResponse>){

            itemView.exerciseNameTV.text = exerciseReservation.exerciseTitle
            itemView.exerciseDurationTv.text = exerciseReservation.duration
           itemView.creationDateTV.text= exerciseReservation.creationDate

            itemView.setOnClickListener { listener.onItemClick(exerciseReservation) }
        }

    }

    object DiffCallback : DiffUtil.ItemCallback<ExerciseReservationResponse>() {
        override fun areItemsTheSame(oldItem: ExerciseReservationResponse, newItem: ExerciseReservationResponse): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ExerciseReservationResponse, newItem: ExerciseReservationResponse): Boolean {
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