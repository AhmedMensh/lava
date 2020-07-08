package com.android.pharous.app.lava.ui.invitation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer

import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.common.pickDateDialog
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_invitation.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class InvitationFragment : Fragment() {

    private val viewModel: InvitationViewModel by viewModel()

    private var isItMailInvitation = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_invitation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.error.observe(viewLifecycleOwner, Observer {

            it?.let {
                Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
            }
        })
        mailInvitationTV.setOnClickListener {

            isItMailInvitation = true
            showInvitationDialog()
        }

        smsInvitationTV.setOnClickListener {
            isItMailInvitation = false
            showInvitationDialog()
        }
    }

    private fun showInvitationDialog() {

        val builder = AlertDialog.Builder(context!!)
        val view = activity?.layoutInflater?.inflate(R.layout.dialog_invitation, null)

        val fullNameET = view?.findViewById<EditText>(R.id.fullNameET)
        val mobileNumberET = view?.findViewById<EditText>(R.id.mobileNumberET)
        val emailET = view?.findViewById<EditText>(R.id.emailET)
        val emailTI = view?.findViewById<TextInputLayout>(R.id.emailTI)

        if (isItMailInvitation) emailTI?.visibility = View.VISIBLE
        else emailTI?.visibility = View.GONE



        builder.setView(view)
        val dialog = builder.create()
        view?.findViewById<Button>(R.id.sendBtn)?.setOnClickListener {

            if (isItMailInvitation) {
                viewModel.inviteFriendByMail(
                    fullNameET?.text.toString(),
                    emailET?.text.toString()
                    , mobileNumberET?.text.toString()
                ).observe(viewLifecycleOwner, Observer {


                    it?.let {
                        dialog.dismiss()
                        Toast.makeText(context, it.result, Toast.LENGTH_LONG).show()
                    }
                })
            } else {
                viewModel.inviteFriendBySMS(
                    fullNameET?.text.toString()
                    , mobileNumberET?.text.toString()
                ).observe(viewLifecycleOwner, Observer {


                    it?.let {
                        dialog.dismiss()
                        Toast.makeText(context, it.result, Toast.LENGTH_LONG).show()
                    }
                })
            }
        }


        dialog.show()
    }

}
