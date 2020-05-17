package com.android.pharous.app.lava.ui.workout

import CardioProgrameDetails
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.models.ExerciseReservationResponse
import com.chauthai.swipereveallayout.SwipeRevealLayout
import com.chauthai.swipereveallayout.ViewBinderHelper
import kotlinx.android.synthetic.main.cardio_class_item.view.*

class CardioClassesAdapter : ListAdapter<CardioProgrameDetails,CardioClassesAdapter.ViewHolder>(DiffCallback) {


    private var viewBinderHelper : ViewBinderHelper = ViewBinderHelper()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun render(item: CardioProgrameDetails?) {

            itemView.cardionIndexTV.text = "${position + 1}"
            itemView.cardioTypeTV.text = item?.equipment?.nameEN
            itemView.cardioInfoTV.text = "${item?.duration} Min ${item?.speed} Speed"
        }

        var swipeRevealLayout = itemView.findViewById<SwipeRevealLayout>(R.id.swipeRevealLayout)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cardio_class_item, parent, false))
    }

    object DiffCallback : DiffUtil.ItemCallback<CardioProgrameDetails>() {
        override fun areItemsTheSame(oldItem: CardioProgrameDetails, newItem: CardioProgrameDetails): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CardioProgrameDetails, newItem: CardioProgrameDetails): Boolean {
            return oldItem == newItem
        }
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        viewBinderHelper.bind(holder.swipeRevealLayout,position.toString())

        holder.render(getItem(position))
    }
}