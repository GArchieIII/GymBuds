package com.example.gymbuds.firestore

import android.util.Log
import com.example.gymbuds.BudRegisterActivity
import com.example.gymbuds.MainActivity
import com.example.gymbuds.models.Bud
import com.example.gymbuds.utils.Constants
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FirestoreClass {
    private val mFirestore= FirebaseFirestore.getInstance()

    fun registerBud(activity:BudRegisterActivity,budInfo:Bud){
        mFirestore.collection(Constants.BUDS)
            .document(budInfo.budId)
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
}
