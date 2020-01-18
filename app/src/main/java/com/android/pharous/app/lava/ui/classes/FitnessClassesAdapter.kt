package com.android.pharous.app.lava.ui.classes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.pharous.app.lava.R
import com.chauthai.swipereveallayout.SwipeRevealLayout
import com.chauthai.swipereveallayout.ViewBinderHelper

class FitnessClassesAdapter : RecyclerView.Adapter<FitnessClassesAdapter.ViewHolder>() {


    private var viewBinderHelper : ViewBinderHelper = ViewBinderHelper()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var swipeRevealLayout = itemView.findViewById<SwipeRevealLayout>(R.id.swipeRevealLayout)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fitness_class_item, parent, false))
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        viewBinderHelper.bind(holder.swipeRevealLayout,position.toString())
    }
}