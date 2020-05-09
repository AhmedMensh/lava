package com.android.pharous.app.lava.ui.membership


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog

import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.common.Constants
import com.android.pharous.app.lava.common.SharedPreferencesManager
import com.android.pharous.app.lava.models.MembershipInfoResponse
import kotlinx.android.synthetic.main.fragment_membership.*

/**
 * A simple [Fragment] subclass.
 */
class MembershipFragment : Fragment(R.layout.fragment_membership) {

    private val membershipServicesAdapter by lazy { MembershipServicesAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userNameTv.text = context?.let { SharedPreferencesManager.getStringValue(it,Constants.USER_NAME) }
        arguments?.let {
            it.getParcelable<MembershipInfoResponse>("membership_info")?.let { it1 -> renderData(it1) }
        }
        suspendBtn.setOnClickListener { showMembershipSuspensionDialog() }

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

        var builder = AlertDialog.Builder(context!!)
        var view =  activity?.layoutInflater?.inflate(R.layout.dialog_membership_suspension, null)


        builder.setView(view)
        var dialog = builder.create()
        view?.findViewById<Button>(R.id.bookBtn)?.setOnClickListener {

            dialog.dismiss()
        }


        dialog.show()
    }
}
