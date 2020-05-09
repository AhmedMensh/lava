package com.android.pharous.app.lava.ui.measurement


import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.common.IItemClickListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_measurement.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class MeasurementFragment : Fragment(R.layout.fragment_measurement),IItemClickListener<String> {

    private val viewModel : MeasurementViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        analysisRV.setHasFixedSize(true)
        analysisRV.adapter = MeasurementsAdapter(this)


        val content = SpannableString("SEE FUL HISTORY")
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        fullHistoryLabel.text = content
        fullHistoryLabel.setOnClickListener { findNavController().navigate(R.id.action_measurementFragment_to_measurementHistoryFragment) }

        viewModel.error.observe(viewLifecycleOwner , Observer {
            it?.let {
                Toast.makeText(context,it,Toast.LENGTH_LONG).show()
            }
        })

        viewModel.isLoading.observe(viewLifecycleOwner , Observer {

            it?.let {
                if (it) activity?.progressBar?.visibility = View.VISIBLE
                else activity?.progressBar?.visibility = View.GONE
            }
        })

        viewModel.getMemberMeasurements().observe(viewLifecycleOwner , Observer {

            it?.let {
                Log.e("getMemberMeasurements","$it")
            }
        })


        viewModel.getMemberInBodyResults().observe(viewLifecycleOwner , Observer {

            it?.let {
                Log.e("getMemberInBodyResults","$it")
            }
        })
    }

    override fun onItemClick(item: String) {
        findNavController().navigate(R.id.weightLogHistoryFragment)
    }


}
