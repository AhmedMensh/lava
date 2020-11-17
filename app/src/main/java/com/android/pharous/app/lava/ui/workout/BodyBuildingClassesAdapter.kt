package com.android.pharous.app.lava.ui.workout

import com.android.pharous.app.lava.ui.workout.models.BodybuildingProgramDetails
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.pharous.app.lava.R
import com.chauthai.swipereveallayout.SwipeRevealLayout
import com.chauthai.swipereveallayout.ViewBinderHelper
import kotlinx.android.synthetic.main.strength_class_item.view.*

class BodyBuildingClassesAdapter(private val listener : BodyBuildingListener) : ListAdapter<BodybuildingProgramDetails,BodyBuildingClassesAdapter.ViewHolder>(DiffCallback) {

    interface BodyBuildingListener{
        fun onBodBuildingItemClick(item : BodybuildingProgramDetails)
        fun onBodBuildingHowIconClick(item : BodybuildingProgramDetails)
    }

    private var viewBinderHelper : ViewBinderHelper = ViewBinderHelper()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var swipeRevealLayout = itemView.findViewById<SwipeRevealLayout>(R.id.swipeRevealLayout)

        fun bind(item: BodybuildingProgramDetails, listener: BodyBuildingListener){

            itemView.strengthNameTV.text = item?.equipment?.nameEN
            itemView.durationTV.text = "${item.duration}"
            itemView.foregroundCL.setOnClickListener { listener.onBodBuildingItemClick(item) }

        }

    }


    object DiffCallback : DiffUtil.ItemCallback<BodybuildingProgramDetails>() {
        override fun areItemsTheSame(oldItem: BodybuildingProgramDetails, newItem: BodybuildingProgramDetails): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: BodybuildingProgramDetails, newItem: BodybuildingProgramDetails): Boolean {
            return oldItem == newItem
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.strength_class_item, parent, false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.bind(getItem(position),listener)
        viewBinderHelper.bind(holder.swipeRevealLayout,position.toString())
    }
}