package com.android.pharous.app.lava.ui.booking


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog

import com.android.pharous.app.lava.R
import kotlinx.android.synthetic.main.fragment_sessions.*

/**
 * A simple [Fragment] subclass.
 */
class SessionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sessions, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        sessionsRV.adapter =
            BookingSessionAdapter()
//        showBookingSessionDialog()
    }


    fun showBookingSessionDialog(){

        var builder = AlertDialog.Builder(context!!)
        var view =  activity?.layoutInflater?.inflate(R.layout.dialog_booking_session, null)


        builder.setView(view)
        var dialog = builder.create()
        view?.findViewById<Button>(R.id.bookBtn)?.setOnClickListener {

            dialog.dismiss()
        }


        dialog.show()
    }
}
