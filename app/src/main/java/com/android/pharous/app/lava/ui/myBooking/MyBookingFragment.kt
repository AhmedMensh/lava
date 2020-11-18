package com.android.pharous.app.lava.ui.myBooking


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer

import com.android.pharous.app.lava.R
import kotlinx.android.synthetic.main.fragment_my_booking.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class MyBookingFragment : Fragment(R.layout.fragment_my_booking) {

    private val viewModel : MyBookingViewModel by viewModel()
    private val personalTrainingSessionsAdapter by lazy { PersonalTrainingSessionsAdapter() }
    private val upcomingBookingsAdapter by lazy { UpcomingBookingsAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backArrowImgV.setOnClickListener { activity?.onBackPressed() }
        viewModel.error.observe(viewLifecycleOwner, Observer {
            it?.let {
                Toast.makeText(requireContext(),it,Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.getPersonalTrainingSessions().observe(viewLifecycleOwner, Observer {
            it?.let {
                ptSessionsRV.setHasFixedSize(true)
                ptSessionsRV.adapter = personalTrainingSessionsAdapter
                personalTrainingSessionsAdapter.submitList(it)
            }
        })


        viewModel.getExerciseReservations().observe(viewLifecycleOwner, Observer {
            it?.let {
                exerciseRV.setHasFixedSize(true)
                exerciseRV.adapter = upcomingBookingsAdapter
                upcomingBookingsAdapter.submitList(it)
            }
        })
    }

}
