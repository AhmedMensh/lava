package com.android.pharous.app.lava.ui.training

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.ui.training.models.ExerciseScheduleResponse
import kotlinx.android.synthetic.main.training_item.view.*

class TrainingAdapter(private val isFirstItem : Boolean = false,private val viewModel: TrainingViewModel) :
    ListAdapter<ExerciseScheduleResponse,TrainingAdapter.ViewHolder>(DiffCallback) {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.training_item,
                parent,
                false
            )
        )
//        if (isFirstItem){
//            return ViewHolder(
//                LayoutInflater.from(parent.context).inflate(
//                    R.layout.training_item,
//                    parent,
//                    false
//                )
//            )
//        }else {
//            return ViewHolder(
//                LayoutInflater.from(parent.context).inflate(
//                    R.layout.shrink_training_item,
//                    parent,
//                    false
//                )
//            )
//        }
    }

    object DiffCallback : DiffUtil.ItemCallback<ExerciseScheduleResponse>() {
        override fun areItemsTheSame(oldItem: ExerciseScheduleResponse, newItem: ExerciseScheduleResponse): Boolean {
            return oldItem.iD == newItem.iD
        }

        override fun areContentsTheSame(oldItem: ExerciseScheduleResponse, newItem: ExerciseScheduleResponse): Boolean {
            return oldItem == newItem
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindData(position,isFirstItem,getItem(position),viewModel)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

        fun bindData(
            position: Int,
            isFirstItem: Boolean,
            item: ExerciseScheduleResponse,
            viewModel: TrainingViewModel
        ){


            itemView.trainTypeTV.text = item.exerciseTitle
            itemView.coachNameTV.text = item.coachName
            itemView.trainingScheduleTV.text = item.date

            itemView.setOnClickListener { item.exerciseID?.let { it1 ->
                viewModel.reserveExercise(
                    it1
                ).observe(itemView.context as LifecycleOwner, Observer {
                    it?.let {
                        Toast.makeText(itemView.context,"Reservation has been added to your schedule",Toast.LENGTH_LONG).show()
                    }
                })
            } }
//            if (position%2 == 0 && isFirstItem){
//                itemView.mainViewCL.visibility = View.INVISIBLE
//            }

        }


    }
}