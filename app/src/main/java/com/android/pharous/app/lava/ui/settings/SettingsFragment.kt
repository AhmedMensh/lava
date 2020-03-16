package com.android.pharous.app.lava.ui.settings


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.pharous.app.lava.R
import kotlinx.android.synthetic.main.fragment_settings.*


/**
 * A simple [Fragment] subclass.
 */
class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        membershipCL.setOnClickListener { findNavController().navigate(R.id.action_settingsFragment_to_membershipFragment) }
        myPointCL.setOnClickListener { findNavController().navigate(R.id.action_settingsFragment_to_pointFragment) }
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

}
