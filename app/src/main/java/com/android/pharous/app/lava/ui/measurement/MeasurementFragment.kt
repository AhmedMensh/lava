package com.android.pharous.app.lava.ui.measurement


import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.common.IItemClickListener
import kotlinx.android.synthetic.main.fragment_measurement.*

/**
 * A simple [Fragment] subclass.
 */
class MeasurementFragment : Fragment(),IItemClickListener<String> {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_measurement, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        analysisRV.setHasFixedSize(true)
        analysisRV.adapter = MeasurementsAdapter(this)


        val content = SpannableString("SEE FUL HISTORY")
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        fullHistoryLabel.text = content
        fullHistoryLabel.setOnClickListener { findNavController().navigate(R.id.action_measurementFragment_to_measurementHistoryFragment) }
    }

    override fun onItemClick(item: String) {
        findNavController().navigate(R.id.weightLogHistoryFragment)
    }


}
