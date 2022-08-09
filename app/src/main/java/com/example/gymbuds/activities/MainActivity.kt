package com.example.gymbuds.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.example.gymbuds.R
import com.example.gymbuds.firestore.FirestoreClass
import com.example.gymbuds.models.Bud
import com.example.gymbuds.utils.Constants
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txt_register.setOnClickListener(this)
        btnLogin.setOnClickListener(this)
    }
    fun signInBud(){
        if(validateInput()){
            showProgressDialog("Please Wait")
            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                bud_username.text.toString().trim{it<' '},
                bud_password.text.toString().trim{it<' '})

                .addOnCompleteListener {
                    if(it.isSuccessful){
                        FirestoreClass().getBudDetails(this@MainActivity)
                    }else{
                        hideProgressDialog()
                        showErrorsnackBar(it.exception!!.message.toString(),true)
                    }
                }




        }


    }

    fun budSignInSuccess(bud: Bud){
        hideProgressDialog()
        if(bud.profCompleted==0){
            val intent=Intent(this@MainActivity,ProfileActivity::class.java)
            intent.putExtra(Constants.EXTRA_BUD_DETAILS,bud)
            startActivity(intent)
        }

    }

    override fun onClick(p0: View?) {
        if(p0!=null){
            when(p0.id){
                R.id.btnLogin ->{
                signInBud()




                }

                R.id.txt_register ->{
                    intent= Intent(this@MainActivity, BudRegisterActivity::class.java)
                    startActivity(intent)
                }
                R.id.txt_forgotPassword ->{

                    startActivity(Intent(this@MainActivity,ForgotPasswordActivity::class.java))

                }

            }
        }
    }

    fun validateInput():Boolean{
        return when{
            TextUtils.isEmpty(bud_username.text.toString().trim{it<=' '})->{
                showErrorsnackBar(resources.getString(R.string.err_enter_username),true)
                false
            }
            TextUtils.isEmpty(bud_password.text.toString().trim{it<=' '})->{
                showErrorsnackBar(resources.getString(R.string.err_enter_password),true)
                false
            }else->{
                true
            }

        }
    }


}