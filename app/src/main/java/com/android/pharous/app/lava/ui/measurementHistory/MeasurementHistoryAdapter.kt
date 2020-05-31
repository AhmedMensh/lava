package com.android.pharous.app.lava.ui.measurementHistory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.ui.measurement.MemberMeasurementResponse
import kotlinx.android.synthetic.main.measurement_history_item.view.*

class MeasurementHistoryAdapter : ListAdapter<MemberMeasurementResponse,MeasurementHistoryAdapter.ViewHolder>(DiffCallback) {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item : MemberMeasurementResponse) {

            itemView.creationDateTV.text = item.creationDate?.split(" ")?.get(0) ?: ""
            itemView.armValueTV.text = "${item.arm} CM"
            itemView.breastValueTV.text = "${item.chest} CM"
            itemView.westValueTV.text = "${item.waist} CM"
            itemView.hipsValueTV.text = "${item.buttocks} CM"
            itemView.thightsValueTV.text = "${item.thigh} CM"
            itemView.legsValueTV.text = "${item.calf} CM"
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.measurement_history_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object DiffCallback : DiffUtil.ItemCallback<MemberMeasurementResponse>() {
        override fun areItemsTheSame(oldItem: MemberMeasurementResponse, newItem: MemberMeasurementResponse): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MemberMeasurementResponse, newItem: MemberMeasurementResponse): Boolean {
            return oldItem == newItem
        }
    }
}