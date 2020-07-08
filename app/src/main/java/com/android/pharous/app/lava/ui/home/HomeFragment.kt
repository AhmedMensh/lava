package com.android.pharous.app.lava.ui.home


import android.content.Context
import android.hardware.*
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.common.IItemClickListener
import com.android.pharous.app.lava.models.ExerciseReservationResponse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment(R.layout.fragment_home),
    IItemClickListener<ExerciseReservationResponse>  {


    private val viewModel: HomeViewModel by viewModel()
//    private val viewModel: HomeViewModel by inject { parametersOf(this)  }

    private val adapter: UpcomingBookingsAdapter by lazy { UpcomingBookingsAdapter(this) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        swiperefresh.setOnRefreshListener {
            getProfile()
            getExerciseReservations()
            getMemberShipInfo()
            getStepCounts()
            swiperefresh.isRefreshing = false
        }
        cardioCL.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("type", "cardio")
            findNavController().navigate(R.id.action_homeFragment_to_workoutFragment, bundle)
        }
        strengthCL.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("type", "")
            findNavController().navigate(R.id.action_homeFragment_to_workoutFragment, bundle)
        }


        viewModel.error.observe(viewLifecycleOwner, Observer {

            it?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        })


        viewModel.isLoading.observe(viewLifecycleOwner, Observer {

            it?.let {
                if (it) activity?.progressBar?.visibility = View.VISIBLE
                else activity?.progressBar?.visibility = View.GONE
            }
        })

        getProfile()
        getExerciseReservations()
        getMemberShipInfo()
        getStepCounts()
    }

    private fun getProfile() {
        viewModel.getProfile().observe(viewLifecycleOwner, Observer {

            it?.let {
                userNameTv.text = it.fullName
            }
        })
    }

    private fun getExerciseReservations() {
        viewModel.getExerciseReservations().observe(viewLifecycleOwner, Observer {

            it?.let {
                upcomingBookingRV.adapter = adapter
                upcomingBookingRV.setHasFixedSize(true)
                adapter.submitList(it )
            }
        })
    }

    private fun getMemberShipInfo() {

        viewModel.getMembershipInfo().observe(viewLifecycleOwner, Observer {
            it?.let {

            }
        })
    }

    private fun getStepCounts(){
        activity?.let {
            val sensorManager = it.getSystemService(Context.SENSOR_SERVICE) as SensorManager
            val mSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
            val triggerEventListener = object : TriggerEventListener() {
                override fun onTrigger(event: TriggerEvent?) {
                    // Do work
                   event?.let {

                       Toast.makeText(context,"${event.values[0]}",Toast.LENGTH_LONG).show()
//                       Toast.makeText(context,"${event.values[1]}",Toast.LENGTH_LONG).show()
//                       Toast.makeText(context,"${event.values[2]}",Toast.LENGTH_LONG).show()
                   }
                }
            }
            mSensor?.also { sensor ->
                sensorManager.requestTriggerSensor(triggerEventListener, sensor)
            }
        }

    }
    override fun onItemClick(item: ExerciseReservationResponse) {

        showCancelBookingDialog(item)
    }

    private fun showCancelBookingDialog(item: ExerciseReservationResponse) {

        Log.e("ID","${item.iD}")
        var builder = AlertDialog.Builder(context!!)
        var view = activity?.layoutInflater?.inflate(R.layout.dialog_cancel_booking, null)
        builder.setView(view)
        var dialog = builder.create()

        view?.findViewById<TextView>(R.id.exerciseNameTV)?.text = item.exerciseTitle
        view?.findViewById<TextView>(R.id.exerciseDurationTV)?.text = "${item.duration} Min"
        view?.findViewById<TextView>(R.id.exerciseDateTV)?.text = item.date
        view?.findViewById<ImageView>(R.id.cancelImgV)?.setOnClickListener { dialog.dismiss() }
        view?.findViewById<Button>(R.id.cancelReservationBtn)?.setOnClickListener {

            item.iD?.let { it1 -> viewModel.updateReservations(it1,"1")
                .observe(viewLifecycleOwner , Observer {

                    it?.let {
                        Toast.makeText(context,"Reservation has been canceled", Toast.LENGTH_SHORT).show()
                    }
                }) }
             }



        dialog.show()
    }
}
