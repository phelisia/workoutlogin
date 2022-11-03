package dev.phelisia.workoutlog.ui

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import dev.phelisia.workoutlog.R
import dev.phelisia.workoutlog.models.ExerciseCategory

class CategoryAdapter (context: Context,var categories:List<ExerciseCategory>):ArrayAdapter<ExerciseCategory>(context,0){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)

    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }
    fun getCustomView(position:Int,convertView: View?,parent: ViewGroup):View{
        val view=LayoutInflater.from(context).inflate(R.layout.custom_spinner_item,parent)
        val tvSpinnerText=view.findViewById<TextView>(R.id.tvspinnerText)
        tvSpinnerText.text=categories.get(position).categoryNmae
        return view

    }
    }

