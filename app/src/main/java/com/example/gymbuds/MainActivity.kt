package com.example.gymbuds

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.example.gymbuds.activities.BaseActivity
import com.example.gymbuds.activities.ForgotPasswordActivity
import com.example.gymbuds.firestore.FirestoreClass
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txt_register.setOnClickListener(this)
    }
    fun validateInput():Boolean{
        return when{
            TextUtils.isEmpty(bud_username.text.toString().trim{it>' '}) ->{
                false
            }
            TextUtils.isEmpty(bud_password.text.toString().trim{it>' '})->{
                false
            }
            else->{
                true
            }

        }
    }

    fun signInBud(){
        if(validateInput()){
            showProgressDialog("Please Wait")
            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                bud_username.text.toString().trim{it<' '},
                bud_password.text.toString().trim{it<' '})

                .addOnCompleteListener {
                    if(it.isSuccessful){


                    }
                }




        }


    }

    override fun onClick(p0: View?) {
        if(p0!=null){
            when(p0.id){
                R.id.btnLogin->{





                }

                R.id.txt_register->{
                    intent= Intent(this@MainActivity,BudRegisterActivity::class.java)
                    startActivity(intent)
                }
                R.id.txt_forgotPassword->{

                    startActivity(Intent(this@MainActivity,ForgotPasswordActivity::class.java))

                }

            }
        }
    }


}