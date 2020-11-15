package com.android.pharous.app.lava.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.models.ProfileRequest
import com.android.pharous.app.lava.models.ProfileResponse
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.cityTV
import kotlinx.android.synthetic.main.fragment_profile.emailET
import kotlinx.android.synthetic.main.fragment_profile.phoneNumberET
import kotlinx.android.synthetic.main.fragment_profile.userNameET
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val profileRequest by lazy { ProfileRequest() }
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

        saveBtn.setOnClickListener { updateProfile() }
    }

    private fun getCities() {
        viewModel.getCities().observe(viewLifecycleOwner, Observer {

            it?.let {
                val adapter = ArrayAdapter<String>(
                    context!!,
                    R.layout.support_simple_spinner_dropdown_item,
                    it.map { it.nameEN }
                )

                cityTV?.setAdapter(adapter)
                cityTV.onItemClickListener = AdapterView.OnItemClickListener { _, _, poition, _ ->
                    cityId = it[poition].iD ?: -1

                }
            }
        })

    }

    private fun getRegions() {
        viewModel.getRegions().observe(viewLifecycleOwner, Observer {

            it?.let {
                val adapter = ArrayAdapter<String>(
                    context!!,
                    R.layout.support_simple_spinner_dropdown_item,
                    it.map { it.nameEN }
                )

                countryTV?.setAdapter(adapter)
                countryTV.onItemClickListener = AdapterView.OnItemClickListener { _, _, poition, _ ->
                    countryId = it[poition].iD ?: -1
                }
            }
        })

    }

    private fun getNationalities() {
        viewModel.getNationalities().observe(viewLifecycleOwner, Observer {

            it?.let {
                val adapter = ArrayAdapter<String>(
                    context!!,
                    R.layout.support_simple_spinner_dropdown_item,
                    it.map { it.nameEN }
                )

                nationalityTV?.setAdapter(adapter)
                nationalityTV.onItemClickListener = AdapterView.OnItemClickListener { _, _, poition, _ ->
                    nationalityId = it[poition].iD ?: -1
                }
            }
        })

    }


    private fun getJobTitles() {
        viewModel.getJobTitles().observe(viewLifecycleOwner, Observer {

            it?.let {
                val adapter = ArrayAdapter<String>(
                    context!!,
                    R.layout.support_simple_spinner_dropdown_item,
                    it.map { it.titleEN }
                )

                jobTitleTV?.setAdapter(adapter)
                jobTitleTV.onItemClickListener = AdapterView.OnItemClickListener { _, _, poition, _ ->
                    jobTitleId = it[poition].iD ?: -1
                }
            }
        })

    }

    private fun getBranches() {
        viewModel.getBranches().observe(viewLifecycleOwner, Observer {

            it?.let {
                val adapter = ArrayAdapter<String>(
                    context!!,
                    R.layout.support_simple_spinner_dropdown_item,
                    it.map { it.nameEN }
                )


                branchTV?.setAdapter(adapter)
                branchTV.onItemClickListener = AdapterView.OnItemClickListener { _, _, poition, _ ->
                    branchId = it[poition].iD ?: -1
                }
            }
        })

    }
    private fun getGoals(){


        viewModel.getGoals().let {
            val adapter = ArrayAdapter<String>(
                context!!,
                R.layout.support_simple_spinner_dropdown_item,
                it.map { it }
            )

            goalTV?.setAdapter(adapter)

        }
    }

    private fun getLanguages(){


        viewModel.getLanguages().let {
            val adapter = ArrayAdapter<String>(
                context!!,
                R.layout.support_simple_spinner_dropdown_item,
                it.map { it }
            )


            languageTV?.setAdapter(adapter)

        }
    }

    private fun getProfileInfo() {
        viewModel.getProfile().observe(viewLifecycleOwner, Observer {

            it?.let {
                bindData(it)
                getCities()
                getRegions()
                getNationalities()
                getJobTitles()
                getBranches()
                getGoals()
                getLanguages()
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


    private fun updateProfile(){
        profileRequest.apply {
            cityID = cityId.toString()
            regionID = countryId.toString()
            nationalityID = nationalityId.toString()
            branchID = branchId.toString()
            identityID = identityTV.text.toString()
            birthDate = birthDayTI.text.toString()
            fullName = userNameET.text.toString()
            email = emailET.text.toString()
            emergencyPhone = emergencyPhoneET.text.toString()
            mobileNumber = phoneNumberET.text.toString()
            jobTitleID = jobTitleId.toString()
            language = languageTV.text.toString()
            goal = goalTV.text.toString()

        }
        viewModel.updateUserProfile(profileRequest).observe(viewLifecycleOwner, Observer {
            it?.let {
                getProfileInfo()
            }
        })
    }
}