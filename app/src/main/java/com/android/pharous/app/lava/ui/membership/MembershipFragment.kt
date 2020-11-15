package com.android.pharous.app.lava.ui.membership


import android.os.Bundle
import android.util.Log
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
import androidx.navigation.fragment.findNavController

import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.common.Constants
import com.android.pharous.app.lava.common.SharedPreferencesManager
import com.android.pharous.app.lava.common.pickDateDialog
import com.android.pharous.app.lava.models.MembershipInfoResponse
import kotlinx.android.synthetic.main.fragment_membership.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class MembershipFragment : Fragment(R.layout.fragment_membership) {

    private val membershipServicesAdapter by lazy { MembershipServicesAdapter() }
    private val viewModel : MembershipViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userNameTv.text = context?.let { SharedPreferencesManager.getStringValue(it,Constants.USER_NAME) }
        arguments?.let {
            it.getParcelable<MembershipInfoResponse>("membership_info")?.let { it1 -> renderData(it1) }
        }
        suspendBtn.setOnClickListener { checkMembershipSuspension() }

        viewProfileTV.setOnClickListener { findNavController().navigate(R.id.action_membershipFragment_to_profileFragment) }
        viewModel.error.observe(viewLifecycleOwner, Observer {

                it?.let {
                    Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun checkMembershipSuspension(){

        viewModel.checkMembershipSuspension().observe(viewLifecycleOwner , Observer {
            it?.let {
                showMembershipSuspensionDialog()
            }
        })
    }

    private fun renderData(membership: MembershipInfoResponse){
        membershipServicesRV.setHasFixedSize(true)
        membershipServicesRV.adapter = membershipServicesAdapter
        membershipServicesAdapter.submitList(membership.services?.values?.toList())

        startDateTV.text = "Member Since ${membership.startDate}"
        endDateTV.text = "End in  ${membership.endDate }"
        membershipDurationTV.text = "${membership.period} Day Membership"
        branchTV.text = membership.branchName
    }

    private fun showMembershipSuspensionDialog(){

        val builder = AlertDialog.Builder(context!!)
        val view =  activity?.layoutInflater?.inflate(R.layout.dialog_membership_suspension, null)

        val startDateTV =  view?.findViewById<TextView>(R.id.startDateTV)
        val endDateTV =  view?.findViewById<TextView>(R.id.endDateTV)

        startDateTV?.setOnClickListener { pickDateDialog(startDateTV) }
        endDateTV?.setOnClickListener { pickDateDialog(endDateTV) }


        builder.setView(view)
        val dialog = builder.create()
        view?.findViewById<Button>(R.id.suspendBtn)?.setOnClickListener {

           viewModel.suspendMembership(startDateTV?.text.toString() , endDateTV?.text.toString())
               .observe(viewLifecycleOwner, Observer {

                   it?.let {
                       Toast.makeText(context,it.result,Toast.LENGTH_SHORT).show()
                       dialog.dismiss()
                   }
               })
        }


        dialog.show()
    }
}
