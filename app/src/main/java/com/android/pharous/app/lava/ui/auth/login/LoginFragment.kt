package com.android.pharous.app.lava.ui.auth.login


import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.common.Constants
import com.android.pharous.app.lava.common.SharedPreferencesManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel : LoginViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val content = SpannableString("Sign Up")
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        signUpTV.text = content


        SharedPreferencesManager.setStringValue(context!!,Constants.TOKEN,"7382e0474bcb43d731405e2a7e4f491e")
        findNavController().navigate(R.id.homeFragment)

        signUpTV.setOnClickListener { findNavController().navigate(R.id.action_loginFragment_to_registerFragment) }

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
        signInBtn.setOnClickListener {
            if (phoneNumberET.text.isEmpty()){
                Toast.makeText(context,getString(R.string.phone_number_required),Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.login(phoneNumberET.text.toString()).observe(this , Observer {

                it?.let {
                    val bundle = Bundle()
                    bundle.putString("phone_number",phoneNumberET.text.toString())
                    bundle.putString("code",it.verificationCode.toString().trim())
                    bundle.putString("token",it.accessToken)
                    findNavController().navigate(R.id.action_loginFragment_to_phoneVerficationFragment,bundle)

                }
            })
             }
    }
}
