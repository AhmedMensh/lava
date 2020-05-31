package com.android.pharous.app.lava.ui.measurementHistory


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.ui.measurement.MeasurementViewModel
import com.android.pharous.app.lava.ui.measurement.MemberMeasurementResponse
import kotlinx.android.synthetic.main.fragment_measurement_history.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class MeasurementHistoryFragment : Fragment(R.layout.fragment_measurement_history) {



    private val viewModel : MeasurementViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMemberMeasurements().observe(viewLifecycleOwner , Observer {
            it?.let {
                val adapter = MeasurementHistoryAdapter()
                measurementHistoryRV.adapter = adapter
                measurementHistoryRV.setHasFixedSize(true)
                adapter.submitList(it)
            }
        })


    }

}
