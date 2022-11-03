package dev.phelisia.workoutlog.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.phelisia.workoutlog.databinding.RowExerciseBinding
import dev.phelisia.workoutlog.models.Exercise

class TrackAdapter(val exerciseList:List<Exercise>):RecyclerView.Adapter<TrackAdapter.ExerciseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val binding=RowExerciseBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ExerciseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val currentExercise=exerciseList.get(position)
        holder.binding.textView2.text=currentExercise.exerciseName
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class ExerciseViewHolder(val binding:RowExerciseBinding):RecyclerView.ViewHolder(binding.root)
}