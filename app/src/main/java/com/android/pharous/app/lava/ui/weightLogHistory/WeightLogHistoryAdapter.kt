package com.android.pharous.app.lava.ui.weightLogHistory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.ui.measurement.MemberMeasurementResponse
import com.android.pharous.app.lava.ui.training.models.SessionResponse
import kotlinx.android.synthetic.main.weight_history_item.view.*

class WeightLogHistoryAdapter : ListAdapter<MemberMeasurementResponse, WeightLogHistoryAdapter.ViewHolder>(DiffCallback) {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item : MemberMeasurementResponse){

            itemView.creationDateTV.text = item.creationDate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.weight_history_item, parent, false))
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