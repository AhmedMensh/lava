package com.android.pharous.app.lava.ui.booking


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.android.pharous.app.lava.R
import kotlinx.android.synthetic.main.fragment_booking.*

/**
 * A simple [Fragment] subclass.
 */
class BookingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_booking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startClassesFragment()
        classesTV.setOnClickListener {
            commingBookedClassesLabel.visibility = View.VISIBLE
            commingBookedClassesTI.visibility = View.VISIBLE
            yogaImgV.visibility = View.VISIBLE
            swimmingImgV.visibility = View.VISIBLE
            startClassesFragment() }
        sessionsTV.setOnClickListener {
            commingBookedClassesLabel.visibility = View.GONE
            commingBookedClassesTI.visibility = View.GONE
            yogaImgV.visibility = View.GONE
            swimmingImgV.visibility = View.GONE
            startSessionsFragment()}
    }

    private fun startSessionsFragment(){

        val sessionsFragment = SessionsFragment()
        val fm = activity?.supportFragmentManager
        val ft = fm?.beginTransaction()
        ft?.replace(R.id.containerFragment,sessionsFragment)
        ft?.commit()
    }

    private fun startClassesFragment(){

        val calssesFragment = ClassesScheduleFragment()
        val fm = activity?.supportFragmentManager
        val ft = fm?.beginTransaction()
        ft?.replace(R.id.containerFragment,calssesFragment)
        ft?.commit()
    }
}
