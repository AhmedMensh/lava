package com.android.pharous.app.lava.ui.booking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.common.IItemClickListener
import kotlinx.android.synthetic.main.booking_session_item.view.*

class BookingSessionAdapter(private val listener : IItemClickListener<String>) : RecyclerView.Adapter<BookingSessionAdapter.ViewHolder>() {




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

        holder.bindData(listener)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) , View.OnClickListener {

        fun bindData(listener: IItemClickListener<String>) {

            itemView.setOnClickListener(this)
            itemView.bookTV.setOnClickListener { listener.onItemClick("") }
        }

        override fun onClick(p0: View?) {

            if (itemView.sessionDescTV.visibility == View.VISIBLE)
                itemView.sessionDescTV.visibility = View.GONE
            else
                itemView.sessionDescTV.visibility = View.VISIBLE
        }
    }
}