package com.android.pharous.app.lava.ui.training

import android.app.Activity
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.ui.training.models.ExerciseScheduleResponse
import kotlinx.android.synthetic.main.training_day_item.view.*


class ExerciseScheduleAdapter() : RecyclerView.Adapter<ExerciseScheduleAdapter.ViewHolder>() {


    private var exerciseData: Map<String?, List<ExerciseScheduleResponse>> = HashMap()

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
        return exerciseData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindData(
            position,
            exerciseData.keys.toList()[position],
            exerciseData.values.toList().get(position)
        )


//
//        //if you need same height as width you can set devicewidth in holder.image_view.getLayoutParams().height
//        //if you need same height as width you can set devicewidth in holder.image_view.getLayoutParams().height
//        holder.image_view.getLayoutParams().height = deviceheight
    }

    fun submitDate(exerciseData: Map<String?, List<ExerciseScheduleResponse>>) {

        this.exerciseData = exerciseData
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bindData(
            position: Int,
            date: String?,
            exerciseData: List<ExerciseScheduleResponse>
        ) {

            itemView.currentDayTV.text = date
            var trainingAdapter = TrainingAdapter(true)
            itemView.trainingRV.adapter = trainingAdapter
            trainingAdapter.submitList(exerciseData)

            Log.e("Position", "$position")
            val displaymetrics = DisplayMetrics()
            (itemView.context as Activity).windowManager.defaultDisplay.getMetrics(displaymetrics)
            //if you need three fix imageview in width
            //if you need three fix imageview in width
            val devicewidth = displaymetrics.widthPixels / 2
            itemView.currentDayTV.layoutParams.width = devicewidth
            itemView.currentDayTV.setTextColor(itemView.context.resources.getColor(R.color.white))
            itemView.currentDayTV.background =
                itemView.context.resources.getDrawable(R.drawable.rect_left_corner_orange)


//            itemView.background =
//                itemView.context.getDrawable(R.drawable.rect_sharp_corner_off_white)


        }


    }
}