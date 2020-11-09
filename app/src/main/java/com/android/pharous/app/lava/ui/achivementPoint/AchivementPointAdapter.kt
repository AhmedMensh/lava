package com.android.pharous.app.lava.ui.achivementPoint

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.models.PointHistoryResponse
import com.android.pharous.app.lava.models.Service
import kotlinx.android.synthetic.main.achivement_point_item.view.*

class AchivementPointAdapter : ListAdapter<PointHistoryResponse,AchivementPointAdapter.ViewHolder>(DiffCallback) {


    object DiffCallback : DiffUtil.ItemCallback<PointHistoryResponse>() {
        override fun areItemsTheSame(oldItem: PointHistoryResponse, newItem: PointHistoryResponse): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PointHistoryResponse, newItem: PointHistoryResponse): Boolean {
            return oldItem == newItem
        }
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item :PointHistoryResponse ){

            itemView.numberTV.text = "${adapterPosition + 1}"
            val date = item.creationDate?.split(" ")
            itemView.creationDateTV.text = date?.get(0) ?: ""
            itemView.numberOfPointTV.text = "${item.numberOfPoint}"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.achivement_point_item, parent, false))
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(getItem(position))
    }
}