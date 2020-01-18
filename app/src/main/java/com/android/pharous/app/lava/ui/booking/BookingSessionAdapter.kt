package com.android.pharous.app.lava.ui.booking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.pharous.app.lava.R
import kotlinx.android.synthetic.main.booking_session_item.view.*

class BookingSessionAdapter : RecyclerView.Adapter<BookingSessionAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) , View.OnClickListener {

         fun bindData(){

            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {

            if (itemView.sessionDescTV.visibility == View.VISIBLE)
                itemView.sessionDescTV.visibility = View.GONE
            else
                itemView.sessionDescTV.visibility = View.VISIBLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.booking_session_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindData()
    }
}