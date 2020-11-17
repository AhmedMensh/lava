package com.android.pharous.app.lava.ui.measurementHistory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.common.IItemClickListener
import com.android.pharous.app.lava.ui.measurement.MemberInbodyresultResponse
import kotlinx.android.synthetic.main.measurement_history_item.view.*

class MeasurementHistoryAdapter(private val listener : IItemClickListener<MemberInbodyresultResponse>) :
    ListAdapter<MemberInbodyresultResponse, MeasurementHistoryAdapter.ViewHolder>(DiffCallback) {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(
            item: MemberInbodyresultResponse,
            position: Int,
            listener: IItemClickListener<MemberInbodyresultResponse>
        ) {

            if (position == 0) {
                itemView.apply {
                    creationDateTV.text = "Date"
                    waterTV.text = "Water"
                    fatTV.text = "Fat"
                    fatControlTV.text = "Fat Control"
                    fatLevelTV.text = "Fat Level"
                    smmTV.text = "SMM"
                    weightTV.text = "Weight"
                    targetWeightTV.text = "Target Weight"
                    weightControlTV.text = "Weight Control"
                    muscleControlTV.text = "Muscle Control"
                    rateTV.text = "Rate"
                    hipTV.text = "Hip"
                    editTV.visibility = View.VISIBLE
                    editImgV.visibility = View.GONE
                }
            } else {
                itemView.apply {
                    creationDateTV.text = item.creationDate?.split(" ")?.get(0) ?: ""
                    waterTV.text = "${item.totalBodyWater}"
                    fatTV.text = "${item.bodyFatMass}"
                    fatControlTV.text = "${item.fatControl}"
                    fatLevelTV.text = "${item.visceralFatLevel}"
                    smmTV.text = "${item.sMM}"
                    weightTV.text = "${item.weight}"
                    targetWeightTV.text = "${item.targetWeight}"
                    weightControlTV.text = "${item.weightControl}"
                    muscleControlTV.text = "${item.muscleControl}"
                    rateTV.text = "${item.basalMetabolicRate}"
                    hipTV.text = "${item.waistHipRatio}"
                    editTV.visibility = View.GONE
                    editImgV.visibility = View.VISIBLE
                    editImgV.setOnClickListener { listener.onItemClick(item) }


                }

            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.measurement_history_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position,listener)
    }

    object DiffCallback : DiffUtil.ItemCallback<MemberInbodyresultResponse>() {
        override fun areItemsTheSame(
            oldItem: MemberInbodyresultResponse,
            newItem: MemberInbodyresultResponse
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: MemberInbodyresultResponse,
            newItem: MemberInbodyresultResponse
        ): Boolean {
            return oldItem == newItem
        }
    }
}