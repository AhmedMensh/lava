package com.android.pharous.app.lava.ui.schedule


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController

import com.android.pharous.app.lava.R
import kotlinx.android.synthetic.main.fragment_schedule.*

/**
 * A simple [Fragment] subclass.
 */
class ScheduleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sessionRV.adapter = SessionsScheduleAdapter()

        swimmingClassCL.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("type","swimming")
            findNavController().navigate(R.id.action_scheduleFragment_to_bookingFragment) }

        fittnessClassCL.setOnClickListener {    var bundle = Bundle()
            bundle.putString("type","class")
            findNavController().navigate(R.id.action_scheduleFragment_to_bookingFragment) }
    }

    fun showBookingClassDialog(){

        var builder = AlertDialog.Builder(context!!)
        var view =  activity?.layoutInflater?.inflate(R.layout.dialog_booking_class, null)


        builder.setView(view)
        var dialog = builder.create()
        view?.findViewById<Button>(R.id.bookBtn)?.setOnClickListener {

            showVervicationDialog()
            dialog.dismiss()
        }


        dialog.show()
    }

    private fun showVervicationDialog() {

        var builder = AlertDialog.Builder(context!!)
        var view =  activity?.layoutInflater?.inflate(R.layout.dialog_verifcation, null)


        builder.setView(view)


        var dialog = builder.create()
        dialog.show()
    }

}
