package com.example.gymbuds.firestore

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import com.example.gymbuds.activities.BudRegisterActivity
import com.example.gymbuds.activities.MainActivity
import com.example.gymbuds.activities.ProfileActivity
import com.example.gymbuds.activities.SettingsActivity
import com.example.gymbuds.models.Bud
import com.example.gymbuds.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class FirestoreClass {
    private val mFirestore= FirebaseFirestore.getInstance()

    fun registerBud(activity: BudRegisterActivity, budInfo:Bud){
        mFirestore.collection(Constants.BUDS)
            .document(budInfo.id)
            .set(budInfo, SetOptions.merge())
            .addOnSuccessListener{
                activity.budRegisterSuccess()

            }
            .addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while registering the user",
                    e
                )
            }
    }
    fun getCurrentBudID():String{
        val currentBud= FirebaseAuth.getInstance().currentUser
        var currentBudID=""
        if(currentBud!=null){
            currentBudID=currentBud.uid
        }
        return currentBudID

    }
    fun getBudDetails(activity: Activity){
        mFirestore.collection(Constants.BUDS)
            .document(getCurrentBudID())
            .get()
            .addOnSuccessListener{
                Log.i(activity.javaClass.simpleName,it.toString())
                val bud=it.toObject(Bud::class.java)!!

                val sharedPreferences=
                    activity.getSharedPreferences(
                        Constants.MYBUD_PREFERENCES,
                        Context.MODE_PRIVATE
                    )
                val editor: SharedPreferences.Editor=sharedPreferences.edit()
                editor.putString(
                    Constants.LOGGED_IN_BUDNAME,
                    "${bud.first_name} ${bud.last_name}"
                )
                editor.apply()
                when(activity){
                    is ProfileActivity->{
                        activity.budDetailsSuccess(bud)
                    }
                    is MainActivity ->{
                        activity.budSignInSuccess(bud)

                    }


                }
            }
            .addOnFailureListener(){

                when(activity){
                    is SettingsActivity->{
                        activity.hideProgressDialog()
                    }

                }


            }
        fun uploadImageToCloudStorage(activity:Activity, imageFileURI: Uri?, imageType:String){
            val sRef: StorageReference = FirebaseStorage.getInstance().reference.child(
                imageType+System.currentTimeMillis()+"."+
                        Constants.getFileExtension(
                            activity,imageFileURI
                        )
            )
            sRef.putFile(imageFileURI!!)
                .addOnSuccessListener{
                    Log.e("Firebase Image URL", it.metadata!!.reference!!.downloadUrl.toString())
                    it.metadata!!.reference!!.downloadUrl.addOnSuccessListener{
                        Log.e("Downloadable Image Url",it.toString())
                        when(activity){
                            is ProfileActivity ->{
                                activity.imageUploadSuccess(it.toString())
                            }
                        }
                    }

                }


        }


    }

    fun updateBudDetails(activity:Activity,budHashMap:HashMap<String,Any>){


    }
}
