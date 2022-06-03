package com.example.gymbuds.firestore

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

            }
            .addOnFailureListener{


            }
    }
}
