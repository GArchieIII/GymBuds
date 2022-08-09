package com.example.gymbuds.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize

 data class Bud(
     val id:String="",
    val first_name:String="",
    val last_name:String="",
    val profPic:String="",
    val profCompleted:Int=0,
    val city:String="",
    val state:String="",
    val  fitGoal:String="",
    val myGym:String="",
    val gender:String="",
    val profileURL:String="",
     val email:String="",
     val password:String=""

): Parcelable {
}