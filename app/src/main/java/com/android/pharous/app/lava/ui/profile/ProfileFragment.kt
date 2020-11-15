package com.android.pharous.app.lava.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.models.ProfileResponse
import kotlinx.android.synthetic.main.dialog_cancel_booking.*
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val viewModel : ProfileViewModel by viewModel()
    private var countryId = -1
    private var cityId = -1
    private var jobTitleId = -1
    private var branchId = -1
    private var nationalityId = -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.error.observe(viewLifecycleOwner, Observer {
            it?.let {
                Toast.makeText(requireContext(),it,Toast.LENGTH_SHORT).show()
            }
        })

        getProfileInfo()

    }

    private fun getProfileInfo() {
        viewModel.getProfile().observe(viewLifecycleOwner, Observer {

            it?.let {
                bindData(it)
            }
        })
    }

    private fun bindData(profile: ProfileResponse) {
        userNameET.setText(profile.fullName)
        countryTV.setText(profile.regionName)
        identityTV.text = profile.identityID.toString()
        countryId = profile.regionID ?: -1
        cityTV.setText(profile.cityName)
        cityId = profile.cityID ?: -1
        emergencyPhoneET.setText(profile.emergencyPhone)
        phoneNumberET.setText(profile.mobileNumber.toString())
        emailET.setText(profile.email)
        birthDayTI.text = profile.birthDate
        jobTitleTV.setText(profile.jobTitleName)
        jobTitleId = profile.jobTitleID ?: -1
        goalTV.setText(profile.goal)
        branchTV.setText(profile.branchName)
        branchId = profile.branchID ?: -1
        nationalityTV.setText(profile.nationalityName)
        nationalityId = profile.nationalityID ?: -1
        languageTV.setText(profile.language)
    }


}