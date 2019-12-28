package com.android.pharous.app.lava.ui.auth.phoneVerification


import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.android.pharous.app.lava.R
import kotlinx.android.synthetic.main.fragment_phone_verification.*


/**
 * A simple [Fragment] subclass.
 */
class phoneVerificationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phone_verification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val content = SpannableString("Sign Up")
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        signUpTV.text = content

        signUpTV.setOnClickListener { findNavController().navigate(R.id.action_phoneVerficationFragment_to_registerFragment) }

        sendBtn.setOnClickListener { findNavController().navigate(R.id.action_phoneVerficationFragment_to_homeFragment) }
        changeTV.setOnClickListener { findNavController().navigate(R.id.action_phoneVerficationFragment_to_loginFragment) }
    }


}
