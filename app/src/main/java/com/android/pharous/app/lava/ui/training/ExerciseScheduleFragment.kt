package com.android.pharous.app.lava.ui.training


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer

import com.android.pharous.app.lava.R
import kotlinx.android.synthetic.main.fragment_exercise_schedule.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class ExerciseScheduleFragment : Fragment(R.layout.fragment_exercise_schedule) {

    private val viewModel : TrainingViewModel by viewModel()
    private val adapter by lazy {  ExerciseScheduleAdapter()}
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.error.observe(viewLifecycleOwner , Observer {
            it?.let {
                Toast.makeText(context,it,Toast.LENGTH_LONG).show()
            }
        })
        viewModel.getExerciseSchedules().observe(viewLifecycleOwner , Observer {

            it?.let {
                Log.i("Ecrcise","${it.groupBy { it.date?.split(" ")?.get(0) }.keys}")
                exerciseScheduleRV.adapter = adapter
                adapter.submitDate(it.groupBy { it.date?.split(" ")?.get(0)})

            }
        })

    }
}