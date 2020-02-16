package com.android.pharous.app.lava.ui.myBooking


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.android.pharous.app.lava.R
import kotlinx.android.synthetic.main.fragment_my_booking.*

/**
 * A simple [Fragment] subclass.
 */
class MyBookingFragment : Fragment(R.layout.fragment_my_booking) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myBookingRV.setHasFixedSize(true)
        myBookingRV.adapter = MyBookingAdapter()
    }

}
