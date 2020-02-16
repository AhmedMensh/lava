package com.android.pharous.app.lava.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.common.IItemClickListener
import kotlinx.android.synthetic.main.workout_item.view.*

class UpcomingBookingsAdapter(private val listener : IItemClickListener<String>) : RecyclerView.Adapter<UpcomingBookingsAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(listener: IItemClickListener<String>){

            itemView.setOnClickListener { listener.onItemClick("") }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.upcoming_booking_item, parent, false))
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(listener)
    }
}