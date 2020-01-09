package com.android.pharous.app.lava.ui.membership


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog

import com.android.pharous.app.lava.R

/**
 * A simple [Fragment] subclass.
 */
class MembershipFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_membership, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showGymRatingDialog()
    }
    private fun showGymRatingDialog() {

        var builder = AlertDialog.Builder(context!!)
        var view =  activity?.layoutInflater?.inflate(R.layout.dialog_gym_rating, null)


        builder.setView(view)


        var dialog = builder.create()
        dialog.show()
    }
}
