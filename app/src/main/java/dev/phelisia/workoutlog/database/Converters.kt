package dev.phelisia.workoutlog.database

import androidx.room.TypeConverter



class Converters {
    @TypeConverter
    fun listToString(listx:List<String>):String{
//        var outputString=""
//        listx.forEach{item->
//            outputString+=item+"$item,"
//
//        }
//        return  outputString
        return listx.joinToString (",")
    }
    @TypeConverter
    fun stringToList(stringx:String):List<String>{
        return  stringx.split(",")
    }

}