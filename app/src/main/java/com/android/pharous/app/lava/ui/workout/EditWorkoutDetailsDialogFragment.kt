package com.android.pharous.app.lava.ui.workout

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.models.BodyBuildingRequest
import com.android.pharous.app.lava.models.CardioRequest
import kotlinx.android.synthetic.main.fragment_edit_workout_details_dialog.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class EditWorkoutDetailsDialogFragment : DialogFragment() {

    private val viewModel: WorkoutViewModel by sharedViewModel()
    private var workoutType = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_workout_details_dialog, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            workoutType = it.getString(WORKOUT_TYPE) ?: ""

        }

    }
    override fun onStart() {
        super.onStart()
        dialog?.let {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            it.window?.setLayout(width, height)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (workoutType == CARDIO) cardioGroup.visibility = View.VISIBLE
        else bodBuildingGroup.visibility = View.VISIBLE
        handelClickListeners()
    }

    private fun handelClickListeners() {

        minutesIncrementImgV.setOnClickListener { increment(minutesNumberTV) }
        minutesDecrementImgV.setOnClickListener { decrement(minutesNumberTV) }

        caloriesIncrementImgV.setOnClickListener { increment(caloriesNumberTV) }
        caloriesDecrementImgV.setOnClickListener { decrement(caloriesNumberTV) }

        heartRateIncrementImgV.setOnClickListener { increment(heartRateNumberTV) }
        heartRateDecrementImgV.setOnClickListener { decrement(heartRateNumberTV) }

        speedIncrementImgV.setOnClickListener { increment(speedNumberTV) }
        speedDecrementImgV.setOnClickListener { decrement(speedNumberTV) }


        levelIncrementImgV.setOnClickListener { increment(levelNumberTV) }
        levelDecrementImgV.setOnClickListener { decrement(levelNumberTV) }

        reputationsIncrementImgV.setOnClickListener { increment(reputationsTV) }
        reputationsDecrementImgV.setOnClickListener { decrement(reputationsTV) }

        numberOfReputationsIncrementImgV.setOnClickListener { increment(numberOfReputationsTV) }
        numberOfReputationsDecrementImgV.setOnClickListener { decrement(numberOfReputationsTV) }

        closeImgV.setOnClickListener { dismiss() }
        doneBtn.setOnClickListener {
            if (workoutType == CARDIO)
            viewModel.cardioRequest.value = CardioRequest(
                duration = minutesNumberTV.text.toString(),
                heartRate = heartRateNumberTV.text.toString(),
                level = levelNumberTV.text.toString(),
                calories = caloriesNumberTV.text.toString(),
                speed = speedNumberTV.text.toString()
            )
            else viewModel.bodyBuildingRequest.value = BodyBuildingRequest(
                duration = minutesNumberTV.text.toString(),
                calories = caloriesNumberTV.text.toString(),
                repetition = reputationsTV.text.toString(),
                numberOfRepetition = numberOfReputationsTV.text.toString()
            )
        }

    }

    private fun increment(tv: TextView) {
        tv.text = "${tv.text.toString().trim().toInt().plus(1)}"
    }


    private fun decrement(tv: TextView) {
        if (tv.text.toString().trim().toInt() == 1) return

        tv.text = "${tv.text.toString().trim().toInt().minus(1)}"
    }


    companion object {
        const val WORKOUT_TYPE = "workoutType"
        const val CARDIO = "cardio"
        fun newInstance(workoutType: String) =  EditWorkoutDetailsDialogFragment().apply {
            arguments = Bundle().apply {
                putString(WORKOUT_TYPE, workoutType)
            }
        }
    }
}