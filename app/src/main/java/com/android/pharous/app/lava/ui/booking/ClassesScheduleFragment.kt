package com.android.pharous.app.lava.ui.booking


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.android.pharous.app.lava.R
import kotlinx.android.synthetic.main.fragment_classes_schedule.*

/**
 * A simple [Fragment] subclass.
 */
class ClassesScheduleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_classes_schedule, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var adapter = DaysAdapter(context!!)
        trainingDaysRV.adapter = adapter
    }
}
