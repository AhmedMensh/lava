package com.android.pharous.app.lava.ui.point


import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.common.Constants
import com.android.pharous.app.lava.common.SharedPreferencesManager
import com.android.pharous.app.lava.common.pickDateDialog
import com.android.pharous.app.lava.models.BranchResponse
import com.android.pharous.app.lava.models.MembershipInfoResponse
import com.android.pharous.app.lava.models.PointHistoryResponse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_point.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class PointFragment : Fragment(R.layout.fragment_point) {


    private val viewModel: PointViewModel by viewModel()
    private var branchesList: List<BranchResponse> = emptyList()
    private var pointHistoryList: List<PointHistoryResponse> = emptyList()
    private var dialogView : View ? = null
    private var selectedPackageId = ""
    private var selectedPeriodId = -1
    private var selectedBranchId = -1
    private var contractType = -1 //0 === membership , 1=== services

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {
            it.getParcelable<MembershipInfoResponse>("membership_info")?.let { it1 -> renderData(it1) }
        }
        newMembershipCL.setOnClickListener {
            contractType = 0
            showCreateNewServiceDialog() }

        newServiceCL.setOnClickListener {
            contractType = 1
            showCreateNewServiceDialog("Create New Service") }

        detailsTV.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelableArrayList("list",pointHistoryList as ArrayList<out Parcelable>)
            findNavController().navigate(R.id.action_pointFragment_to_achivementPointFragment,bundle) }



        viewModel.error.observe(viewLifecycleOwner, Observer {

            it?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        })

        viewModel.getTotalPoints().observe(viewLifecycleOwner , Observer {

            it?.let {
                totalAchievementPointLabel.text = "Total Achievement Point : ${it.totalPoint}"
                packageTypeTV.text = "Package : ${it.currentLevel}"
                userNameTv.text = SharedPreferencesManager.getStringValue(requireContext(),Constants.USER_NAME)
            }
        })
        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) activity?.progressBar?.visibility = View.VISIBLE
                else activity?.progressBar?.visibility = View.GONE
            }
        })

        viewModel.getPointHistory().observe(viewLifecycleOwner , Observer {

            it?.let {
                Log.e("LIST","$it")
                pointHistoryList = it
            }
        })
        viewModel.getBranches().observe(viewLifecycleOwner, Observer {

            it?.let {
                branchesList = it
            }
        })
    }

    private fun renderData(it1: MembershipInfoResponse) {

        periodTV.text = "Period :${it1.period}"
        durationTV.text = "${it1.startDate} - ${it1.endDate}"
    }

    private fun showCreateNewServiceDialog(title: String = "Create New Membership") {

        var builder = AlertDialog.Builder(context!!)
        dialogView = activity?.layoutInflater?.inflate(R.layout.dialog_create_service, null)
        dialogView?.findViewById<TextView>(R.id.titleTV)?.text = title
        val startDateTV = dialogView?.findViewById<TextView>(R.id.startDateTV)
        val createBtn = dialogView?.findViewById<TextView>(R.id.createBtn)

        startDateTV?.setOnClickListener { pickDateDialog(startDateTV) }

        createBtn?.setOnClickListener { checkStartDate(startDateTV?.text.toString()) }
        builder.setView(dialogView)
        var dialog = builder.create()


        val adapter = ArrayAdapter<String>(
            context!!,
            R.layout.support_simple_spinner_dropdown_item,
            branchesList.map { it.nameEN }
        )
        val branchTV = dialogView?.findViewById<AutoCompleteTextView>(R.id.branchTV)
        branchTV?.setAdapter(adapter)
        branchTV?.onItemClickListener = AdapterView.OnItemClickListener{ parent ,view ,poistion ,id->

            branchesList[poistion].iD?.let { selectedBranchId = it }
            getBranchPackages(selectedBranchId)
        }
        dialogView?.findViewById<ImageView>(R.id.cancelImgV)?.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

    private fun checkStartDate(startDate: String) {
        viewModel.checkStartDate(selectedPeriodId,startDate).observe(viewLifecycleOwner , Observer {

            it?.let {
                viewModel.createContract(selectedPeriodId,selectedBranchId,startDate).observe(viewLifecycleOwner,
                    Observer {

                        it?.let {

                            Toast.makeText(context,"Done",Toast.LENGTH_LONG).show()
                        }

                    })
            }


        })
    }

    private fun getBranchPackages(branchId: Int) {

        viewModel.getBranchPackages(branchId ,contractType).observe(viewLifecycleOwner , Observer {

            it?.let {

                val adapter = ArrayAdapter<String>(
                    context!!,
                    R.layout.support_simple_spinner_dropdown_item,
                    it.map { it.value }
                )
                val packagesTV = dialogView?.findViewById<AutoCompleteTextView>(R.id.packagesTV)
                packagesTV?.setAdapter(adapter)
                packagesTV?.onItemClickListener = AdapterView.OnItemClickListener{ parent ,view ,poistion ,id->

                    selectedPackageId = it.filter { it.value == packagesTV?.text.toString() }.keys.first()
                    getPackagePeriods()
                }
            }
        })
    }

    private fun getPackagePeriods() {

        viewModel.getPackagePeriods(selectedPackageId.toInt()).observe(viewLifecycleOwner, Observer {



                it?.let {


                    val adapter = ArrayAdapter<String>(
                        context!!,
                        R.layout.support_simple_spinner_dropdown_item,
                        it.map { it.value.toString() }
                    )
                    val packagePeriodsTV = dialogView?.findViewById<AutoCompleteTextView>(R.id.packagePeriodsTV)
                    packagePeriodsTV?.setAdapter(adapter)
                    packagePeriodsTV?.onItemClickListener = AdapterView.OnItemClickListener{ parent ,view ,poistion ,id->

//                        selectedPackageId = it.filter { it.value == packagesTV?.text.toString() }.keys.first()
                        getPackageDetails(it.filter { it.value == packagePeriodsTV?.text.toString().toInt() }.keys.first())

                    }
                }

        })
    }

    private fun getPackageDetails(periodId: String) {

        selectedPeriodId = periodId.toInt()

        viewModel.getPackageDetails(selectedPeriodId).observe(viewLifecycleOwner , Observer {

            it?.let {

                dialogView?.findViewById<TextView>(R.id.periodTV)?.text = "Period : ${it.period}"
                dialogView?.findViewById<TextView>(R.id.numberOfPointTV)?.text = "Number Of Points : ${it.numberOfPoint}"
                dialogView?.findViewById<TextView>(R.id.maximumStartingDateTV)?.text = "Maximum Starting Date : ${it.maximumStartingDate}"
            }
        })
    }
}
