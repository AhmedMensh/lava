package com.android.pharous.app.lava.ui.home


import android.content.Context
import android.hardware.*
import android.os.Bundle
import android.os.Parcelable
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
import com.android.pharous.app.lava.ui.workout.models.BodybuildingProgramDetails
import com.android.pharous.app.lava.ui.workout.models.CardioProgrameDetails
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_workout.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment(R.layout.fragment_home),
    IItemClickListener<ExerciseReservationResponse> {

    var cardioList = mutableListOf<CardioProgrameDetails>()
    var strengthList = mutableListOf<BodybuildingProgramDetails>()
    private var isUserHasCardioProgram = false
    private var isUserHasBodyBuildingProgram = false
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
            if (isUserHasCardioProgram){
                var bundle = Bundle()
                bundle.putString("type", "cardio")
                bundle.putParcelableArrayList("cardio_list",cardioList as ArrayList<out Parcelable>)
                findNavController().navigate(R.id.action_homeFragment_to_workoutFragment, bundle)
            }

        }
        strengthCL.setOnClickListener {
            if (isUserHasBodyBuildingProgram){
            var bundle = Bundle()
            bundle.putString("type", "")
            bundle.putParcelableArrayList("strength_list",strengthList as ArrayList<out Parcelable>)
            findNavController().navigate(R.id.action_homeFragment_to_workoutFragment, bundle)
        }
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
        getCaridoPrograms()
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
                adapter.submitList(it)
            }
        })
    }

    private fun getMemberShipInfo() {

        viewModel.getMembershipInfo().observe(viewLifecycleOwner, Observer {
            it?.let {

            }
        })
    }

    private fun getCaridoPrograms() {

        viewModel.getMemberCardioPrograms().observe(viewLifecycleOwner, Observer {

            it?.let {

                it.forEach {
                    it.cardioProgrameDetail?.values?.forEach {
                        isUserHasCardioProgram = true
                        it?.let { cardioList.add(it) }

                    }
                }
                cardioProgramNameTV.text = cardioList[0]?.equipment?.nameEN
                cardioProgramDurationTV.text = "${cardioList[0]?.duration} min"

                Log.e("DATA","$cardioList")
                it.forEach {
                    it.bodybuildingProgrameDetail?.values?.forEach {
                        isUserHasBodyBuildingProgram = true
                        it?.let { strengthList.add(it) }
                    }
                }
                bodBuildingProgramNameTV.text = strengthList[0]?.equipment?.nameEN
                bodBuildingProgramDurationTV.text = "${strengthList[0]?.duration} min"
            }


        })
    }

    private fun getStepCounts() {
        activity?.let {
            val sensorManager = it.getSystemService(Context.SENSOR_SERVICE) as SensorManager
            val mSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
            val triggerEventListener = object : TriggerEventListener() {
                override fun onTrigger(event: TriggerEvent?) {
                    // Do work
                    event?.let {

                        Toast.makeText(context, "${event.values[0]}", Toast.LENGTH_LONG).show()
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

        Log.e("ID", "${item.iD}")
        var builder = AlertDialog.Builder(context!!)
        var view = activity?.layoutInflater?.inflate(R.layout.dialog_cancel_booking, null)
        builder.setView(view)
        var dialog = builder.create()

        view?.findViewById<TextView>(R.id.exerciseNameTV)?.text = item.exerciseTitle
        view?.findViewById<TextView>(R.id.exerciseDurationTV)?.text = "${item.duration} Min"
        view?.findViewById<TextView>(R.id.exerciseDateTV)?.text = item.date
        view?.findViewById<ImageView>(R.id.cancelImgV)?.setOnClickListener { dialog.dismiss() }
        view?.findViewById<Button>(R.id.cancelReservationBtn)?.setOnClickListener {

            item.iD?.let { it1 ->
                viewModel.updateReservations(it1, "1")
                    .observe(viewLifecycleOwner, Observer {

                        it?.let {
                            Toast.makeText(
                                context,
                                "Reservation has been canceled",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
            }
        }



        dialog.show()
    }
}
