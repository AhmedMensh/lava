package com.android.pharous.app.lava.ui.point


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

import com.android.pharous.app.lava.R
import kotlinx.android.synthetic.main.fragment_point.*

/**
 * A simple [Fragment] subclass.
 */
class PointFragment : Fragment(R.layout.fragment_point) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newMembershipCL.setOnClickListener {showCreateNewServiceDialog()  }
        newServiceCL.setOnClickListener { showCreateNewServiceDialog("Create New Service") }
    }

    private fun showCreateNewServiceDialog(title : String = "Create New Membership") {

        var builder = AlertDialog.Builder(context!!)
        var view =  activity?.layoutInflater?.inflate(R.layout.dialog_create_service, null)
        view?.findViewById<TextView>(R.id.titleTV)?.text = title
        builder.setView(view)
        var dialog = builder.create()

        view?.findViewById<ImageView>(R.id.cancelImgV)?.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }
}
