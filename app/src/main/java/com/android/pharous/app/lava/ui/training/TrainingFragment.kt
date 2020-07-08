package com.android.pharous.app.lava.ui.training


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.View

import com.android.pharous.app.lava.R
import kotlinx.android.synthetic.main.fragment_training.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class TrainingFragment : Fragment(R.layout.fragment_training) {

    private val viewModel : TrainingViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments?.getString("type").equals("class")) startClassesFragment()
        else startSessionsFragment()

        classesTV.setOnClickListener { startClassesFragment() }
        sessionsTV.setOnClickListener { startSessionsFragment()}

        searchByName()
    }

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

    private fun searchByName(){


        searchET.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                viewModel.searchKey.value = s.toString()

            }

            override fun afterTextChanged(s: Editable) {}
        })
    }
    private fun startClassesFragment(){

        commingBookedClassesLabel.visibility = View.VISIBLE
        commingBookedClassesTI.visibility = View.VISIBLE
        yogaImgV.visibility = View.VISIBLE
        swimmingImgV.visibility = View.VISIBLE


        val calssesFragment = ExerciseScheduleFragment()
        val fm = activity?.supportFragmentManager
        val ft = fm?.beginTransaction()
        ft?.replace(R.id.containerFragment,calssesFragment)
        ft?.commit()
    }
}
