package com.android.pharous.app.lava.ui.booking

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.android.pharous.app.lava.R
import kotlinx.android.synthetic.main.training_day_item.view.*


class DaysAdapter(private val context: Context) : RecyclerView.Adapter<DaysAdapter.ViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.training_day_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindData(context,position)


//
//        //if you need same height as width you can set devicewidth in holder.image_view.getLayoutParams().height
//        //if you need same height as width you can set devicewidth in holder.image_view.getLayoutParams().height
//        holder.image_view.getLayoutParams().height = deviceheight
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {


        fun bindData(context: Context, position: Int){

            if (position == 0){
                Log.e("Position","$position")
                val displaymetrics = DisplayMetrics()
                (context as Activity).windowManager.defaultDisplay.getMetrics(displaymetrics)
                //if you need three fix imageview in width
                //if you need three fix imageview in width
                val devicewidth = displaymetrics.widthPixels / 2
                itemView.currentDayTV.layoutParams.width = devicewidth
                itemView.currentDayTV.setTextColor(itemView.context.resources.getColor(R.color.white))
                itemView.currentDayTV.background = itemView.context.resources.getDrawable(R.drawable.rect_left_corner_orange)

                var trainingAdapter  = TrainingAdapter(true)
                itemView.trainingRV.adapter = trainingAdapter
                itemView.background = itemView.context.getDrawable(R.drawable.rect_sharp_corner_off_white)


            }else{


                var trainingAdapter  = TrainingAdapter()
                itemView.trainingRV.adapter = trainingAdapter
            }



        }


    }
}