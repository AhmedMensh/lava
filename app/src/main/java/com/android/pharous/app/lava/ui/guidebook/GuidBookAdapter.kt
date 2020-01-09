package com.android.pharous.app.lava.ui.guidebook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.pharous.app.lava.R
import kotlinx.android.synthetic.main.guidebook_item.view.*

class GuidBookAdapter : RecyclerView.Adapter<GuidBookAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) ,View.OnClickListener {

        fun bindData(position : Int){
            itemView.dropdownArrowImgV.setOnClickListener(this)
        }
        override fun onClick(view: View?) {
            when(view?.id){

                R.id.dropdownArrowImgV ->{

                    if (itemView.childCL.visibility == View.VISIBLE)
                        itemView.childCL.visibility = View.GONE

                    else itemView.childCL.visibility = View.VISIBLE
                }
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.guidebook_item, parent, false))
    }

    override fun getItemCount(): Int {
        return 50
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindData(position)
    }

}