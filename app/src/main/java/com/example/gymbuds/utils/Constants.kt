package com.example.gymbuds.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import java.net.URI

object Constants {
    const val BUDS:String="buds"

    const val FIRST_NAME:String="firstName"
    const val LAST_NAME:String="lastName"
    const val FIT_GOAL:String="fitGoal"
    const val EMAIL:String="email"
    const val GENDER:String="gender"
    const val IMAGE:String="image"
    const val MY_GYM:String="myGym"
    const val CITY:String="city"
    const val STATE:String="state"
    const val PROFILE_COMPLETED:String="profileCompleted"
    const val MALE:String="Male"
    const val FEMALE:String="Female"
    const val MYBUD_PREFERENCES:String="BudPrefs"
    const val PICK_IMAGE_RESULT_CODE=2
    const val LOGGED_IN_BUDNAME:String="logged_in_budname"
    const val EXTRA_BUD_DETAILS:String="extra_bud_details"


    fun showImageChooser(activity: Activity){

        val galleryIntent= Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        activity.startActivityForResult(galleryIntent,PICK_IMAGE_RESULT_CODE)

    }

    fun getFileExtension(activity:Activity,uri: Uri?):String?{

        return MimeTypeMap.getSingleton()
            .getExtensionFromMimeType(activity.contentResolver.getType(uri!!))
    }




}