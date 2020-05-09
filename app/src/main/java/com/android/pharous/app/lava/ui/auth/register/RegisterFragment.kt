package com.android.pharous.app.lava.ui.auth.register


import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.models.RegisterRequest
import kotlinx.android.synthetic.main.fragment_register.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : Fragment(R.layout.fragment_register) {

    private val registerRequest : RegisterRequest by lazy { RegisterRequest() }
    private val viewModel : RegisterViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val content = SpannableString("Sign In")
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        signInTV.text = content

        viewModel.error.observe(viewLifecycleOwner , Observer {

            it?.let {
                Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.getCities().observe(viewLifecycleOwner , Observer {

            it?.let {
                val adapter = ArrayAdapter<String>(
                    context!!,
                    R.layout.support_simple_spinner_dropdown_item,
                    it.map { it.nameEN }
                )

                cityTV?.setAdapter(adapter)
                cityTV.onItemClickListener = AdapterView.OnItemClickListener{_,_,_,_ ->

                }
            }
        })

        signUpBtn.setOnClickListener {
//            registerRequest.birthDate = "25-2-1993"
            registerRequest.cityID = "1"
//            registerRequest.email = "a@e.com"
            registerRequest.fullName = "ahmed"
            registerRequest.mobileNumber = "966541114444"
//            registerRequest.nationalityID = "1"
//            registerRequest.regionID = "1"

            viewModel.register(registerRequest).observe(viewLifecycleOwner , Observer {
                it?.let {
                    findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
                }
            })

        }
        signInTV.setOnClickListener { findNavController().navigate(R.id.action_registerFragment_to_loginFragment) }
    }
}
