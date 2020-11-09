package com.android.pharous.app.lava.ui.settings


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.common.Constants
import com.android.pharous.app.lava.common.SharedPreferencesManager
import com.android.pharous.app.lava.models.MembershipInfoResponse
import kotlinx.android.synthetic.main.dialog_offer.view.*
import kotlinx.android.synthetic.main.fragment_settings.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val viewModel : SettingsViewModel by viewModel()
    private var membershipInfoResponse = MembershipInfoResponse()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userNameTv.text = context?.let { SharedPreferencesManager.getStringValue(it,Constants.USER_NAME) }

        viewModel.error.observe(viewLifecycleOwner , Observer {
            it?.let {
                Toast.makeText(context,it,Toast.LENGTH_LONG).show()
            }
        })

        viewModel.getMembershipInfo().observe(viewLifecycleOwner , Observer {

            it?.let {
                membershipInfoResponse = it

                if (isActiveMember(it.endDate!!)) membershipState.visibility = View.VISIBLE
            }
        })
        val bundle = Bundle()
        bundle.putParcelable("membership_info",membershipInfoResponse)
        membershipCL.setOnClickListener {

            findNavController().navigate(R.id.action_settingsFragment_to_membershipFragment,bundle) }

        myPointCL.setOnClickListener { findNavController().navigate(R.id.action_settingsFragment_to_pointFragment,bundle) }
        offersCL.setOnClickListener { findNavController().navigate(R.id.action_settingsFragment_to_offersFragment) }
        inviteCL.setOnClickListener { findNavController().navigate(R.id.action_settingsFragment_to_invitationFragment) }
        shareCL.setOnClickListener { shareLavaApp() }
        guidBookToolsCL.setOnClickListener { findNavController().navigate(R.id.action_settingsFragment_to_guidFragment) }
        guidBookMusclesGroupCL.setOnClickListener { showBodyAnatomyDialog() }
        tellUsCL.setOnClickListener {  showGymRatingDialog() }
        bookingCL.setOnClickListener { findNavController().navigate(R.id.action_settingsFragment_to_myBookingFragment) }
    }

    private fun showGymRatingDialog() {

        var builder = AlertDialog.Builder(context!!)
        var view =  activity?.layoutInflater?.inflate(R.layout.dialog_gym_rating, null)


        builder.setView(view)


        var dialog = builder.create()
        dialog.show()
    }


    private fun showBodyAnatomyDialog() {

        var builder = AlertDialog.Builder(context!!)
        var view =  activity?.layoutInflater?.inflate(R.layout.dialog_body_anatomy, null)


        builder.setView(view)


        var dialog = builder.create()
        dialog.show()
    }
    private fun shareLavaApp() {
        try {
            val i = Intent(Intent.ACTION_SEND)
            i.type = "text/plain"
            i.putExtra(Intent.EXTRA_SUBJECT, "My application name")
            var sAux = "\nLet me recommend you this application\n\n"
            sAux = sAux + "https://play.google.com/store/apps/ \n\n"
            i.putExtra(Intent.EXTRA_TEXT, sAux)
            startActivity(Intent.createChooser(i, "choose one"))
        } catch (e: Exception) {
            Log.e("TAG", "shareOrganizationContent: " + e.message)
        }
    }
    private fun isActiveMember(endDate : String) : Boolean{

        val date = Calendar.getInstance().time
        val sdformat = SimpleDateFormat("yyyy-MM-dd")
        val currentDate = sdformat.format(date)
        val d1: Date = sdformat.parse(currentDate)
        val d2: Date = sdformat.parse(endDate)

        Log.i("DATE" ,"Current Data : $currentDate -- End Date $endDate")
       return when {
            d1 > d2 -> false
            d1 < d2 -> true
            d1.compareTo(d2) == 0 -> true
            else -> false
        }
    }


}
