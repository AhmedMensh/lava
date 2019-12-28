package com.android.pharous.app.lava.ui.auth.login


import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.pharous.app.lava.R
import kotlinx.android.synthetic.main.fragment_login.*


/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val content = SpannableString("Sign Up")
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        signUpTV.text = content


        signUpTV.setOnClickListener { findNavController().navigate(R.id.action_loginFragment_to_registerFragment) }
        signInBtn.setOnClickListener { findNavController().navigate(R.id.action_loginFragment_to_phoneVerficationFragment) }
    }
}
