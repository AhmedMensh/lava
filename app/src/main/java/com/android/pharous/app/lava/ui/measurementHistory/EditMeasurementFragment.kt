package com.android.pharous.app.lava.ui.measurementHistory

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.models.InbodyResultRequest
import com.android.pharous.app.lava.ui.measurement.MeasurementViewModel
import com.android.pharous.app.lava.ui.measurement.MemberInbodyresultResponse
import kotlinx.android.synthetic.main.fragment_edit_measurement.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class EditMeasurementFragment : Fragment(R.layout.fragment_edit_measurement) {

    private val viewModel: MeasurementViewModel by viewModel()
    private val inbodyResultRequest by lazy { InbodyResultRequest() }
    private var updatedResultId : Int = -1
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            it.getParcelable<MemberInbodyresultResponse>("Edit")?.let { it1 -> bindData(it1) }
            titleTV.text = "Edit Result"
        }
        backImgV.setOnClickListener { activity?.onBackPressed() }

        viewModel.error.observe(viewLifecycleOwner, Observer {
            it?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        })
        confirmBtn.setOnClickListener {
            if (checkInputValidation()) {

                inbodyResultRequest.apply {
                    totalBodyWater = waterEt.text.toString().trim()
                    bodyFatMass = fatEt.text.toString().trim()
                    fatControl = fatControlEt.text.toString().trim()
                    visceralFatLevel = fatLevelEt.text.toString().trim()
                    sMM = smmEt.text.toString().trim()
                    weight = weightEt.text.toString().trim()
                    targetWeight = targetWeightEt.text.toString().trim()
                    weightControl = weightControlEt.text.toString().trim()
                    muscleControl = muscleControlEt.text.toString().trim()
                    waistHipRatio = hipEt.text.toString().trim()
                    basalMetabolicRate = basalMetabolicRateET.text.toString().trim()
                    bodyFatPercent = bodyFatPercentET.text.toString().trim()
                }

                if (updatedResultId == -1){
                    viewModel.addMemberInBodyResults(inbodyResultRequest).observe(viewLifecycleOwner,
                        Observer {
                            it?.let {
                                Toast.makeText(
                                    requireContext(),
                                    "Data Updated Successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        })
                }else{
                    inbodyResultRequest.resultID = updatedResultId.toString()
                    viewModel.updateMemberInBodyResults(inbodyResultRequest).observe(viewLifecycleOwner,
                        Observer {
                            it?.let {
                                Toast.makeText(
                                    requireContext(),
                                    "Data Updated Successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        })
                }

            }
        }
    }

    private fun checkInputValidation(): Boolean {

        if (waterEt.text.toString().isNullOrEmpty() ||
            fatEt.text.toString().isNullOrEmpty() ||
            fatControlEt.text.toString().isNullOrEmpty() ||
            fatLevelEt.text.toString().isNullOrEmpty() ||
            bodyFatPercentET.text.toString().isNullOrEmpty() ||
            smmEt.text.toString().isNullOrEmpty() ||
            weightEt.text.toString().isNullOrEmpty() ||
            targetWeightEt.text.toString().isNullOrEmpty() ||
            weightControlEt.text.toString().isNullOrEmpty() ||
            muscleControlEt.text.toString().isNullOrEmpty() ||
            basalMetabolicRateET.text.toString().isNullOrEmpty() ||
            hipEt.text.toString().isNullOrEmpty()
        ) {
            Toast.makeText(requireContext(), "Please Fill All Data", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun bindData(item: MemberInbodyresultResponse) {
        updatedResultId = item.iD ?: -1
        waterEt.setText("${item.totalBodyWater}")
        fatEt.setText("${item.bodyFatMass}")
        fatControlEt.setText("${item.fatControl}")
        fatLevelEt.setText("${item.visceralFatLevel}")
        bodyFatPercentET.setText("${item.bodyFatMass}")
        smmEt.setText("${item.sMM}")
        weightEt.setText("${item.weight}")
        targetWeightEt.setText("${item.targetWeight}")
        weightControlEt.setText("${item.weightControl}")
        muscleControlEt.setText("${item.muscleControl}")
        hipEt.setText("${item.waistHipRatio}")
        basalMetabolicRateET.setText("${item.basalMetabolicRate}")
    }
}