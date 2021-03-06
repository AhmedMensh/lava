package com.android.pharous.app.lava.ui.training


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.android.pharous.app.lava.R
import kotlinx.android.synthetic.main.fragment_exercise_schedule.*
import kotlinx.android.synthetic.main.fragment_training.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class ExerciseScheduleFragment : Fragment(R.layout.fragment_exercise_schedule) {

    private val viewModel : TrainingViewModel by sharedViewModel    ()
    private val adapter by lazy {  ExerciseScheduleAdapter(viewModel)}
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.error.observe(viewLifecycleOwner , Observer {
            it?.let {
                Toast.makeText(context,it,Toast.LENGTH_LONG).show()
            }
        })

        getExerciseSchedules("")

        viewModel.searchKey.observe(viewLifecycleOwner, Observer {

            it?.let {
                getExerciseSchedules(it)
            }
        })
    }

    private fun getExerciseSchedules(searchName : String){
        viewModel.getExerciseSchedules(searchName).observe(viewLifecycleOwner , Observer {

            it?.let {
                var layoutManger = GridLayoutManager(context,1,RecyclerView.HORIZONTAL,false)
                exerciseScheduleRV.adapter = adapter
                exerciseScheduleRV.layoutManager = layoutManger
                adapter.submitDate(it.groupBy { it.date?.split(" ")?.get(0)})

            }
        })
    }



}
