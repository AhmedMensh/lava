package com.android.pharous.app.lava.ui.booking


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog

import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.common.IItemClickListener
import kotlinx.android.synthetic.main.fragment_sessions.*

/**
 * A simple [Fragment] subclass.
 */
class SessionsFragment : Fragment() , IItemClickListener<String>{

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
            BookingSessionAdapter(this)
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

    override fun onItemClick(item: String) {
        showBookingSessionDialog()
    }


}
