package com.android.pharous.app.lava.ui.training


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.android.pharous.app.lava.R
import kotlinx.android.synthetic.main.fragment_training.*

/**
 * A simple [Fragment] subclass.
 */
class TrainingFragment : Fragment(R.layout.fragment_training) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments?.getString("type").equals("class")) startClassesFragment()
        else startSessionsFragment()

        classesTV.setOnClickListener { startClassesFragment() }
        sessionsTV.setOnClickListener { startSessionsFragment()} }

    private fun startSessionsFragment(){

        commingBookedClassesLabel.visibility = View.GONE
        commingBookedClassesTI.visibility = View.GONE
        yogaImgV.visibility = View.GONE
        swimmingImgV.visibility = View.GONE


        val sessionsFragment = SessionsFragment()
        val fm = activity?.supportFragmentManager
        val ft = fm?.beginTransaction()
        ft?.replace(R.id.containerFragment,sessionsFragment)
        ft?.commit()
    }

    private fun startClassesFragment(){

        commingBookedClassesLabel.visibility = View.VISIBLE
        commingBookedClassesTI.visibility = View.VISIBLE
        yogaImgV.visibility = View.VISIBLE
        swimmingImgV.visibility = View.VISIBLE


        val calssesFragment = ClassesScheduleFragment()
        val fm = activity?.supportFragmentManager
        val ft = fm?.beginTransaction()
        ft?.replace(R.id.containerFragment,calssesFragment)
        ft?.commit()
    }
}
