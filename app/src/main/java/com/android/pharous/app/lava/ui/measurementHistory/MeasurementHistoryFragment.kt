package com.android.pharous.app.lava.ui.measurementHistory


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.common.IItemClickListener
import com.android.pharous.app.lava.ui.measurement.MeasurementViewModel
import com.android.pharous.app.lava.ui.measurement.MemberInbodyresultResponse
import com.android.pharous.app.lava.ui.measurement.MemberMeasurementResponse
import kotlinx.android.synthetic.main.fragment_measurement_history.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class MeasurementHistoryFragment : Fragment(R.layout.fragment_measurement_history) ,IItemClickListener<MemberInbodyresultResponse>{


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val historyList = it.getParcelableArrayList<MemberInbodyresultResponse>("History")?.toMutableList()
            historyList?.add(0, MemberInbodyresultResponse())

            val adapter = MeasurementHistoryAdapter(this)
            measurementHistoryRV.adapter = adapter
            measurementHistoryRV.setHasFixedSize(true)
            adapter.submitList(historyList)
        }
//        viewModel.getMemberMeasurements().observe(viewLifecycleOwner , Observer {
//            it?.let {
//                val adapter = MeasurementHistoryAdapter()
//                measurementHistoryRV.adapter = adapter
//                measurementHistoryRV.setHasFixedSize(true)
//                adapter.submitList(it)
//            }
//        })


    }

    override fun onItemClick(item: MemberInbodyresultResponse) {
        val bundle = Bundle()
        bundle.putParcelable("Edit",item)
        findNavController().navigate(R.id.action_measurementHistoryFragment_to_editMeasuremntFragment,bundle)
    }

}
