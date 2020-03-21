package com.android.pharous.app.lava.ui.auth.phoneVerification


import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.models.VerificationCodeRequest
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_phone_verification.*
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass.
 */
class PhoneVerificationFragment : Fragment(R.layout.fragment_phone_verification) {

    private val verificationCodeRequest = VerificationCodeRequest()
    private val viewModel: VerificationViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {

            verificationCodeRequest.mobileNumber = it.getString("phone_number")
            verificationCodeRequest.verificationCode = it.getString("code")
            verificationCodeRequest.accessToken = it.getString("token")
            phoneNumberTV.text = verificationCodeRequest.mobileNumber
            verificationCodeET.setText(verificationCodeRequest.verificationCode)
            Log.e("CODE","$verificationCodeRequest")
        }

        val content = SpannableString("Sign Up")
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        signUpTV.text = content


        viewModel.isLoading.observe(this , Observer {

            it?.let {
                if (it) activity?.progressBar?.visibility = View.VISIBLE
                else activity?.progressBar?.visibility = View.GONE
            }
        })

        viewModel.error.observe(this , Observer {
            it?.let {
                Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
            }
        })
        sendBtn.setOnClickListener {
            viewModel.verifyPhoneNumber(verificationCodeRequest).observe(this, Observer {

                it?.let {
                    findNavController().navigate(R.id.action_phoneVerficationFragment_to_homeFragment)
                }
            })
        }
        signUpTV.setOnClickListener { findNavController().navigate(R.id.action_phoneVerficationFragment_to_registerFragment) }

        changeTV.setOnClickListener { findNavController().navigate(R.id.action_phoneVerficationFragment_to_loginFragment) }
    }


}