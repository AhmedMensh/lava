package com.android.pharous.app.lava.ui.point


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.models.BranchResponse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_point.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class PointFragment : Fragment(R.layout.fragment_point) {


    private val viewModel: PointViewModel by viewModel()
    private var branchesList: List<BranchResponse> = emptyList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newMembershipCL.setOnClickListener { showCreateNewServiceDialog() }
        newServiceCL.setOnClickListener { showCreateNewServiceDialog("Create New Service") }
        detailsTV.setOnClickListener { findNavController().navigate(R.id.action_pointFragment_to_achivementPointFragment) }



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

        viewModel.getBranches().observe(viewLifecycleOwner, Observer {

            it?.let {
                branchesList = it
            }
        })
    }

    private fun showCreateNewServiceDialog(title: String = "Create New Membership") {

        var builder = AlertDialog.Builder(context!!)
        var view = activity?.layoutInflater?.inflate(R.layout.dialog_create_service, null)
        view?.findViewById<TextView>(R.id.titleTV)?.text = title
        builder.setView(view)
        var dialog = builder.create()


        val adapter = ArrayAdapter<String>(
            context!!,
            R.layout.support_simple_spinner_dropdown_item,
            branchesList.map { it.nameEN }
        )
        val branchTV = view?.findViewById<AutoCompleteTextView>(R.id.branchTV)
        branchTV?.setAdapter(adapter)

        view?.findViewById<ImageView>(R.id.cancelImgV)?.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }
}
