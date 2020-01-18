package com.android.pharous.app.lava.ui.booking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.pharous.app.lava.R
import kotlinx.android.synthetic.main.booking_session_item.view.*
import kotlinx.android.synthetic.main.training_day_item.view.*
import kotlinx.android.synthetic.main.training_item.view.*

class TrainingAdapter(private val isFirstItem : Boolean = false) : RecyclerView.Adapter<TrainingAdapter.ViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (isFirstItem){
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.training_item,
                    parent,
                    false
                )
            )
        }else {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.shrink_training_item,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindData(position,isFirstItem)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

        fun bindData(position: Int,isFirstItem: Boolean){

            if (position%2 == 0 && isFirstItem){
                itemView.mainViewCL.visibility = View.INVISIBLE
            }

        }


    }
}