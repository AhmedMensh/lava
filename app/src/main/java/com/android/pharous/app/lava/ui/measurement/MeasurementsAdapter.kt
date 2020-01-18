package com.android.pharous.app.lava.ui.measurement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.common.IItemClickListener

class MeasurementsAdapter(private val listener: IItemClickListener<String>) : RecyclerView.Adapter<MeasurementsAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindData(listener: IItemClickListener<String>){

            itemView.setOnClickListener { listener.onItemClick("") }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.analysis_item, parent, false))
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(listener)
    }
}