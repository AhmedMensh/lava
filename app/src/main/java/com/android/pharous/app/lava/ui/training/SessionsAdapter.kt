package com.android.pharous.app.lava.ui.training

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.common.IItemClickListener
import com.android.pharous.app.lava.ui.training.models.SessionResponse
import kotlinx.android.synthetic.main.session_item.view.*

class SessionsAdapter(private val listener : IItemClickListener<SessionResponse>) :
    ListAdapter<SessionResponse,SessionsAdapter.ViewHolder>(DiffCallback) {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.session_item,
                parent,
                false
            )
        )
    }

    object DiffCallback : DiffUtil.ItemCallback<SessionResponse>() {
        override fun areItemsTheSame(oldItem: SessionResponse, newItem: SessionResponse): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: SessionResponse, newItem: SessionResponse): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindData(getItem(position),listener)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) , View.OnClickListener {

        fun bindData(
            session: SessionResponse,
            listener: IItemClickListener<SessionResponse>
        ) {

            itemView.sessionNameTV.text = session.serviceName
            itemView.setOnClickListener(this)
            itemView.bookTV.setOnClickListener { listener.onItemClick(session) }
        }

        override fun onClick(p0: View?) {

            if (itemView.sessionDescTV.visibility == View.VISIBLE)
                itemView.sessionDescTV.visibility = View.GONE
            else
                itemView.sessionDescTV.visibility = View.VISIBLE
        }
    }
}