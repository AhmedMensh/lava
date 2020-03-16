package com.android.pharous.app.lava.ui.achivementPoint


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.android.pharous.app.lava.R
import kotlinx.android.synthetic.main.fragment_achivement_point.*

/**
 * A simple [Fragment] subclass.
 */
class AchivementPointFragment : Fragment(R.layout.fragment_achivement_point) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        achivementPointRV.adapter = AchivementPointAdapter()
    }

}
