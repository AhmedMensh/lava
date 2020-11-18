package com.android.pharous.app.lava.ui.myBooking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.models.ExerciseReservationResponse
import kotlinx.android.synthetic.main.my_bookin_item.view.*


class UpcomingBookingsAdapter() :
    ListAdapter<ExerciseReservationResponse,UpcomingBookingsAdapter.ViewHolder>(DiffCallback) {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(
            exerciseReservation: ExerciseReservationResponse
        ){

            itemView.apply {
                nameTV.text = exerciseReservation.exerciseTitle
                durationTV.text = "Duration : ${exerciseReservation.duration}"
                dateTV.text = "Date : ${exerciseReservation.creationDate}"
            }
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
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.my_bookin_item, parent, false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(getItem(position))
    }
}