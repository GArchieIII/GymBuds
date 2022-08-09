package com.example.gymbuds.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gymbuds.R
import com.example.gymbuds.models.Bud
import com.example.gymbuds.utils.GlideLoader
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : BaseActivity() {
    lateinit var mBudDetails:Bud
    private var mSelectedImageFileuri: Uri?=null
    private var mUserProfileImageURL:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }
    fun budLoginSuccess(bud: Bud){
        mBudDetails=bud
        hideProgressDialog()
        if(bud.profileURL!=null){
            GlideLoader(this@SettingsActivity).loadUserPicture(bud.profPic,set_userprofpic)
        }else{
            set_userprofpic.setBackgroundResource(R.drawable.ic_user_placeholder)
        }
       /* txt_name.text="${bud.first_name} ${bud.last_name}"
        txt_email.text=bud.email
        txt_myGym.text=bud.myGym
        txt_gender.text=bud.gender*/

    }
}