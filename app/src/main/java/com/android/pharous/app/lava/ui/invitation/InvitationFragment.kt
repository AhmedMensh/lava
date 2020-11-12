package com.android.pharous.app.lava.ui.invitation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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
class InvitationFragment : Fragment(R.layout.fragment_invitation) {

    private val viewModel: InvitationViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.error.observe(viewLifecycleOwner, Observer {

            it?.let {
                Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
            }
        })
        mailInvitationTV.setOnClickListener {

            showInvitationDialog(true)
        }

        smsInvitationTV.setOnClickListener {

            showInvitationDialog()
        }
    }

    private fun showInvitationDialog(isItMailInvitation  : Boolean = false) {

        val builder = AlertDialog.Builder(context!!)
        val view = activity?.layoutInflater?.inflate(R.layout.dialog_invitation, null)

        val mobileNumberET = view?.findViewById<EditText>(R.id.friendPhoneEt)
        val emailET = view?.findViewById<EditText>(R.id.friendEmailET)
        val fullNameET = view?.findViewById<EditText>(R.id.fullNameET)
        val friendEmailAddressTV = view?.findViewById<TextView>(R.id.friendEmailAddressTV)

        if (isItMailInvitation) {
            emailET?.visibility = View.VISIBLE
            friendEmailAddressTV?.visibility = View.VISIBLE
        }
        else {
            emailET?.visibility = View.GONE
            friendEmailAddressTV?.visibility = View.GONE
        }



        builder.setView(view)
        val dialog = builder.create()
        view?.findViewById<Button>(R.id.backBtn)?.setOnClickListener {
            dialog.dismiss()
        }
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
                        showSuccessDialog()
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
                        showSuccessDialog()
                    }
                })
            }
        }


        dialog.show()
    }

    private fun showSuccessDialog(){

        val builder = AlertDialog.Builder(context!!)
        val view = activity?.layoutInflater?.inflate(R.layout.dialog_verifcation, null)


        builder.setView(view)
        val dialog = builder.create()

        view?.findViewById<ImageView>(R.id.closeImgV)?.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }
}
