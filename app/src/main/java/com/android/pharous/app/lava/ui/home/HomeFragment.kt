package com.android.pharous.app.lava.ui.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController

import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.common.IItemClickListener
import com.android.pharous.app.lava.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() , IItemClickListener<String>{

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
        upcomingBookingRV.adapter = UpcomingBookingsAdapter(this)
    }

    override fun onItemClick(item: String) {

        showCancelBookingDialog()
    }

    private fun showCancelBookingDialog() {

        var builder = AlertDialog.Builder(context!!)
        var view =  activity?.layoutInflater?.inflate(R.layout.dialog_cancel_booking, null)


        builder.setView(view)


        var dialog = builder.create()
        dialog.show()
    }
}
