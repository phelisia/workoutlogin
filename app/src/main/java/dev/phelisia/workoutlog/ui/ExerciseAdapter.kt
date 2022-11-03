package dev.phelisia.workoutlog.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import dev.phelisia.workoutlog.R
import dev.phelisia.workoutlog.models.Exercise
import dev.phelisia.workoutlog.models.ExerciseCategory

class ExerciseAdapter (context: Context, var execises:List<Exercise>):
    ArrayAdapter<Exercise>(context,0,execises){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)

    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }
    fun getCustomView(position:Int, convertView: View?, parent: ViewGroup): View {
        val view= LayoutInflater.from(context).inflate(R.layout.customer_spinner_exercises,parent)
        val tvSpinnerText=view.findViewById<TextView>(R.id.tvspinText)
        tvSpinnerText.text=execises.get(position).exerciseName
        return view

    }

}
