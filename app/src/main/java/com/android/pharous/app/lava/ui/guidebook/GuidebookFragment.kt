package com.android.pharous.app.lava.ui.guidebook


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.android.pharous.app.lava.R
import kotlinx.android.synthetic.main.fragment_guidebook.*

/**
 * A simple [Fragment] subclass.
 */
class GuidebookFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guidebook, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        guidBookRV.setHasFixedSize(true)
        guidBookRV.adapter = GuidBookAdapter()
    }

}
