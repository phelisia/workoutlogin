package dev.phelisia.workoutlog.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dev.phelisia.workoutlog.R
import dev.phelisia.workoutlog.databinding.FragmentPlanBinding
import dev.phelisia.workoutlog.databinding.FragmentTrackBinding
import dev.phelisia.workoutlog.viewmodel.WorkoutPlanViewModel


class TrackFragment : Fragment() {
lateinit var binding:FragmentTrackBinding
val workoutplanViewModel: WorkoutPlanViewModel by viewModels ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=FragmentTrackBinding.inflate(layoutInflater,container,false)
        return view.root
    }

    override fun onResume() {
        super.onResume()
    }

}