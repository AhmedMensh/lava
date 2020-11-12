package com.android.pharous.app.lava.ui.training


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer

import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.common.IItemClickListener
import com.android.pharous.app.lava.ui.training.models.SessionResponse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_sessions.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class SessionsFragment : Fragment(R.layout.fragment_sessions) , IItemClickListener<SessionResponse>{

    private val viewModel : TrainingViewModel by viewModel()
    private val sessionsAdapter : SessionsAdapter by lazy { SessionsAdapter(this) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.error.observe(viewLifecycleOwner , Observer {

            it?.let {
                Toast.makeText(context,it,Toast.LENGTH_LONG).show()
            }
        })

        viewModel.isLoading.observe(viewLifecycleOwner , Observer {
            it?.let {
                if (it) activity?.progressBar?.visibility = View.VISIBLE
                else activity?.progressBar?.visibility = View.GONE
            }
        })

        viewModel.getPersonalTrainingSessions().observe(viewLifecycleOwner , Observer {

            it?.let {
                sessionsRV.adapter = sessionsAdapter
                sessionsRV.setHasFixedSize(true)
                sessionsAdapter.submitList(it)

            }
        })


//        showBookingSessionDialog()
    }


    private fun showBookingSessionDialog(){

        var builder = AlertDialog.Builder(context!!)
        var view =  activity?.layoutInflater?.inflate(R.layout.dialog_booking_session, null)


        builder.setView(view)
        var dialog = builder.create()
        view?.findViewById<Button>(R.id.bookBtn)?.setOnClickListener {

            dialog.dismiss()
        }


        dialog.show()
    }

    override fun onItemClick(item: SessionResponse) {
        showBookingSessionDialog()
    }


}
