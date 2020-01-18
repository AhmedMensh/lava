package com.android.pharous.app.lava.ui.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        cardioCL.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("type","swim")
            findNavController().navigate(R.id.action_homeFragment_to_workoutFragment,bundle) }
        strengthCL.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("type","class")
            findNavController().navigate(R.id.action_homeFragment_to_workoutFragment,bundle) }
        upcomingBookingRV.adapter = UpcomingBookingsAdapter()
    }
}
