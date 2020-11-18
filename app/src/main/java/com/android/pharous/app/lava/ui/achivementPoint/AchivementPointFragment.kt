package com.android.pharous.app.lava.ui.achivementPoint


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View

import com.android.pharous.app.lava.R
import com.android.pharous.app.lava.models.PointHistoryResponse
import kotlinx.android.synthetic.main.fragment_achivement_point.*

/**
 * A simple [Fragment] subclass.
 */
class AchivementPointFragment : Fragment(R.layout.fragment_achivement_point) {

    private var pointHistoryList : List<PointHistoryResponse> = emptyList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backArrowImgV.setOnClickListener { activity?.onBackPressed() }
        arguments?.let {

            pointHistoryList = it.getParcelableArrayList("list")!!
            Log.e("LIST","$pointHistoryList")
            val adapter = AchivementPointAdapter()
            achivementPointRV.adapter = adapter
            adapter.submitList(pointHistoryList)
        }

    }

}
