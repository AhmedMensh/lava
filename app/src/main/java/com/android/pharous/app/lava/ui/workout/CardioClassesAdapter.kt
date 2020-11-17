package com.android.pharous.app.lava.ui.workout

import com.android.pharous.app.lava.ui.workout.models.CardioProgrameDetails
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.common.IItemClickListener
import com.chauthai.swipereveallayout.SwipeRevealLayout
import com.chauthai.swipereveallayout.ViewBinderHelper
import kotlinx.android.synthetic.main.cardio_class_item.view.*

class CardioClassesAdapter(private val listener : CardioListener) : ListAdapter<CardioProgrameDetails,CardioClassesAdapter.ViewHolder>(DiffCallback) {


    private var viewBinderHelper : ViewBinderHelper = ViewBinderHelper()

    interface CardioListener{
        fun onCardioItemClicked(item : CardioProgrameDetails)
        fun onCardioHowIconClicked(item : CardioProgrameDetails)
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun render(
            listener : CardioListener,
            item: CardioProgrameDetails?
        ) {

            itemView.cardionIndexTV.text = "${position + 1}"
            itemView.cardioTypeTV.text = item?.equipment?.nameEN
            itemView.cardioInfoTV.text = "${item?.duration} Min ${item?.speed} Speed"

            itemView.howItWorkTV.setOnClickListener { item?.let { it1 -> listener.onCardioHowIconClicked(it1) } }
            itemView.foregroundCL.setOnClickListener { item?.let { it1 -> listener.onCardioItemClicked(it1) } }
        }

//        var swipeRevealLayout = itemView.findViewById<SwipeRevealLayout>(R.id.swipeRevealLayout)



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


//        viewBinderHelper.bind(holder.swipeRevealLayout,position.toString())

        holder.render(listener,getItem(position))
    }
}