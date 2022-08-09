package com.example.gymbuds.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.gymbuds.R
import com.example.gymbuds.firestore.FirestoreClass
import com.example.gymbuds.models.Bud
import com.example.gymbuds.utils.Constants
import com.example.gymbuds.utils.GlideLoader
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : BaseActivity(), View.OnClickListener {
    private lateinit var mBudDetails: Bud
    private var mSelectedImageFileuri: Uri?=null
    private var mUserProfileImageURL:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        getBudDetails()
        if(intent.hasExtra(Constants.EXTRA_BUD_DETAILS)){
            mBudDetails=intent.getParcelableExtra(Constants.EXTRA_BUD_DETAILS)!!
        }
        if(mBudDetails.profCompleted==0){

        }
        btnEdit.setOnClickListener{
            val intent=Intent(this@ProfileActivity,SettingsActivity::class.java)
            intent.putExtra(Constants.EXTRA_BUD_DETAILS,mBudDetails)
            startActivity(intent)
        }
    }

    override fun onClick(p0: View?) {
        if(p0!=null){
            when(p0.id){
                R.id.btnEdit->{
                    val intent= Intent(this@ProfileActivity,SettingsActivity::class.java)
                    intent.putExtra(Constants.EXTRA_BUD_DETAILS,mBudDetails)
                    startActivity(intent)
                }
            }
        }

    }
    fun getBudDetails(){
        showProgressDialog("Please Wait")
        FirestoreClass().getBudDetails(this@ProfileActivity)
    }
    fun imageUploadSuccess(imageURL:String){
        mUserProfileImageURL=imageURL

    }
    fun budDetailsSuccess(bud:Bud){
        mBudDetails=bud
        hideProgressDialog()
        if(bud.profileURL!=null){
            GlideLoader(this@ProfileActivity).loadUserPicture(bud.profPic,img_proPick)
        }else{
            img_proPick.setBackgroundResource(R.drawable.ic_user_placeholder)
        }
        txt_name.text="${bud.first_name} ${bud.last_name}"
        txt_email.text="${bud.email}"
        txt_myGym.text="${bud.myGym}"
        txt_gender.text="${bud.gender}"
    }
}