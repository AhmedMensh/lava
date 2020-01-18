package com.android.pharous.app.lava.ui.workout


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.android.pharous.app.lava.R
import kotlinx.android.synthetic.main.fragment_workout.*

/**
 * A simple [Fragment] subclass.
 */
class WorkoutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments?.getString("type").equals("class")){
            fitnessClassesRV.visibility = View.VISIBLE
            swimmingClassesRV.visibility = View.GONE
        }else{
            fitnessClassesRV.visibility = View.GONE
            swimmingClassesRV.visibility = View.VISIBLE
        }
        fitnessClassesRV.adapter = FitnessClassesAdapter()
        swimmingClassesRV.adapter = SwimmingClassesAdapter()

        var layoutManger = GridLayoutManager(context,7,RecyclerView.VERTICAL,false)
        classesProgressRV.layoutManager = layoutManger
        classesProgressRV.adapter = ClassesProgressAdapter()

        finishWorkoutBtn.setOnClickListener{ showWorkoutCompletionDialog() }
    }

    fun showWorkoutCompletionDialog(){

        var builder = AlertDialog.Builder(context!!)
        var view =  activity?.layoutInflater?.inflate(R.layout.dialog_workout_completion, null)


        builder.setView(view)

        var dialog = builder.create()
        dialog.show()
    }


}
