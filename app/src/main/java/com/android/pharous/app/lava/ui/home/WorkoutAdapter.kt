package com.android.pharous.app.lava.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.pharous.app.lava.R
import kotlinx.android.synthetic.main.workout_item.view.*

class WorkoutAdapter() : RecyclerView.Adapter<WorkoutAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindData(position: Int){
            if (position == 0){
                itemView.background = itemView.context.getDrawable(R.drawable.button_orange_background)
                itemView.text1.setTextColor(itemView.context.resources.getColor(R.color.white))
                itemView.text2.setTextColor(itemView.context.resources.getColor(R.color.white))
                itemView.text3.setTextColor(itemView.context.resources.getColor(R.color.white))
                itemView.text4.setTextColor(itemView.context.resources.getColor(R.color.white))
                itemView.text5.setTextColor(itemView.context.resources.getColor(R.color.white))
                itemView.view.setBackgroundColor(itemView.context.resources.getColor(R.color.white))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.workout_item, parent, false))
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(position)
    }
}