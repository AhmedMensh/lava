package com.android.pharous.app.lava.ui.workout


import BodybuildingProgramDetails
import CardioProgrameDetails
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.common.IItemClickListener
import com.android.pharous.app.lava.ui.workout.models.EvaluateProgramRequest
import kotlinx.android.synthetic.main.fragment_workout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class WorkoutFragment : Fragment(R.layout.fragment_workout) , IItemClickListener<Any>{

    private val viewModel: WorkoutViewModel by viewModel()
    private val strengthAdapter: StrengthClassesAdapter by lazy { StrengthClassesAdapter() }
    private val cardioAdapter: CardioClassesAdapter by lazy { CardioClassesAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.error.observe(viewLifecycleOwner, Observer {
            it?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        })

        viewModel.getMemberCardioPrograms().observe(viewLifecycleOwner, Observer {

            it?.let {

                var cardioList = mutableListOf<CardioProgrameDetails>()
                var strengthList = mutableListOf<BodybuildingProgramDetails>()

                if (arguments?.getString("type").equals("cardio")) {
                    cardioClassesRV.visibility = View.VISIBLE
                    it.forEach {
                        it.cardioProgrameDetail?.values?.forEach {
                            it?.let { cardioList.add(it) }

                        }
                    }
                    cardioClassesRV.adapter = cardioAdapter
                    cardioAdapter.submitList(cardioList)
                } else {

                    strengthClassesRV.visibility = View.VISIBLE
                    it.forEach {
                        it.bodybuildingProgrameDetail?.values?.forEach {

                            it?.let { strengthList.add(it) }
                        }
                    }
                    strengthClassesRV.adapter = strengthAdapter
                    strengthAdapter.submitList(strengthList)
                }


            }
        })


        var layoutManger = GridLayoutManager(context, 7, RecyclerView.VERTICAL, false)
        classesProgressRV.layoutManager = layoutManger
        classesProgressRV.adapter = ClassesProgressAdapter()

        finishWorkoutBtn.setOnClickListener { showWorkoutCompletionDialog() }
    }

    private fun showWorkoutCompletionDialog() {

        var builder = AlertDialog.Builder(context!!)
        var view = activity?.layoutInflater?.inflate(R.layout.dialog_workout_completion, null)

        view?.findViewById<ImageView>(R.id.cryingImgV)?.setOnClickListener { evaluatePrograms("1") }
        view?.findViewById<ImageView>(R.id.happyImgV)?.setOnClickListener { evaluatePrograms("2") }
        view?.findViewById<ImageView>(R.id.inLoveImgV)?.setOnClickListener { evaluatePrograms("3") }
        builder.setView(view)

        var dialog = builder.create()
        dialog.show()
    }


    private fun evaluatePrograms(evaluateLevel: String) {
        var evaluateProgramRequest = EvaluateProgramRequest()
        evaluateProgramRequest.level = evaluateLevel
        evaluateProgramRequest.programID = "26"

        viewModel.evaluateCardioProgram(evaluateProgramRequest)
            .observe(viewLifecycleOwner, Observer {

                it?.let {
                    Toast.makeText(context, "Done", Toast.LENGTH_LONG).show()
                }
            })

    }

    private fun showDescriptionDialog(item: CardioProgrameDetails) {

        var builder = AlertDialog.Builder(context!!)
        var view = activity?.layoutInflater?.inflate(R.layout.dialog_offer, null)

        view?.findViewById<TextView>(R.id.descriptionTV)?.text = item.descriptionEN
        view?.findViewById<TextView>(R.id.titleTV)?.text = item.equipment.nameEN
        view?.findViewById<ImageView>(R.id.equipmentIV)?.visibility = View.VISIBLE
        builder.setView(view)
        var dialog = builder.create()


        dialog.show()
    }

    override fun onItemClick(item: Any) {

        when(item){
            is CardioProgrameDetails -> {
                showDescriptionDialog(item)
            }
        }
    }
}
