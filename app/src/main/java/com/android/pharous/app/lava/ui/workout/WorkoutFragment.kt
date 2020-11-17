package com.android.pharous.app.lava.ui.workout


import com.android.pharous.app.lava.ui.workout.models.BodybuildingProgramDetails
import com.android.pharous.app.lava.ui.workout.models.CardioProgrameDetails
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
import com.android.pharous.app.lava.ui.workout.models.EvaluateProgramRequest
import kotlinx.android.synthetic.main.fragment_workout.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass.
 */
class WorkoutFragment : Fragment(R.layout.fragment_workout) , CardioClassesAdapter.CardioListener,BodyBuildingClassesAdapter.BodyBuildingListener{

    private val viewModel: WorkoutViewModel by sharedViewModel()
    private val strengthAdapter: BodyBuildingClassesAdapter by lazy { BodyBuildingClassesAdapter(this) }
    private val cardioAdapter: CardioClassesAdapter by lazy { CardioClassesAdapter(this) }
    private lateinit var  cardioProgrameDetails : CardioProgrameDetails
    private lateinit var  bodybuildingProgramDetails : BodybuildingProgramDetails
    private var programId : String ? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMemberCardioPrograms()
        addCardioReadout()
        addBodyBuildingReadout()
        viewModel.error.observe(viewLifecycleOwner, Observer {
            it?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        })


        var layoutManger = GridLayoutManager(context, 7, RecyclerView.VERTICAL, false)
        classesProgressRV.layoutManager = layoutManger
        classesProgressRV.adapter = ClassesProgressAdapter()

        finishWorkoutBtn.setOnClickListener { showWorkoutCompletionDialog() }


    }

    private fun addCardioReadout(){
        viewModel.cardioRequest.observe(viewLifecycleOwner, Observer {

            it?.let {
                it.equipmentID = cardioProgrameDetails.equipment.id.toString()
                it.programID = programId
                viewModel.addCardioReadout(it).observe(viewLifecycleOwner, Observer {
                    it?.let {
                        Toast.makeText(requireContext(),"Done",Toast.LENGTH_SHORT).show()
                    }
                })
            }
        })
    }

    private fun addBodyBuildingReadout(){
        viewModel.bodyBuildingRequest.observe(viewLifecycleOwner, Observer {

            it?.let {
                it.equipmentID = bodybuildingProgramDetails.equipment.id.toString()
                it.programID = programId
                viewModel.addBodyBuildingReadout(it).observe(viewLifecycleOwner, Observer {
                    it?.let {
                        Toast.makeText(requireContext(),"Done",Toast.LENGTH_SHORT).show()
                    }
                })
            }
        })
    }
    private fun getMemberCardioPrograms(){
        var cardioList: MutableList<CardioProgrameDetails>
        var strengthList: MutableList<BodybuildingProgramDetails>

        arguments?.let {

            val type = it.getString("type")
            programId = it.getString("programId")

            if (type.equals("cardio")) {
                appBarTitle.text = "Cardio"
                cardioClassesRV.visibility = View.VISIBLE
                cardioClassesRV.adapter = cardioAdapter
                cardioList = it.getParcelableArrayList<CardioProgrameDetails>("cardio_list")!!
                cardioAdapter.submitList(cardioList)
            } else {
                appBarTitle.text = "Body Building"
                strengthClassesRV.visibility = View.VISIBLE
                strengthClassesRV.adapter = strengthAdapter
                strengthList =
                    it.getParcelableArrayList<BodybuildingProgramDetails>("strength_list")!!
                strengthAdapter.submitList(strengthList)

            }
        }
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

        view?.findViewById<TextView>(R.id.descriptionTV)?.text = item?.descriptionEN
        view?.findViewById<TextView>(R.id.titleTV)?.text = item?.equipment?.nameEN
        view?.findViewById<ImageView>(R.id.equipmentIV)?.visibility = View.VISIBLE
        builder.setView(view)
        var dialog = builder.create()


        dialog.show()
    }

    

    override fun onCardioItemClicked(item: CardioProgrameDetails) {
        cardioProgrameDetails = item
        EditWorkoutDetailsDialogFragment
            .newInstance(EditWorkoutDetailsDialogFragment.CARDIO).show(childFragmentManager,"Tag")
    }

    override fun onCardioHowIconClicked(item: CardioProgrameDetails) {
        showDescriptionDialog(item)
    }

    override fun onBodBuildingItemClick(item: BodybuildingProgramDetails) {
        bodybuildingProgramDetails = item
        EditWorkoutDetailsDialogFragment
            .newInstance("").show(childFragmentManager,"Tag")
    }

    override fun onBodBuildingHowIconClick(item: BodybuildingProgramDetails) {
        TODO("Not yet implemented")
    }
}
