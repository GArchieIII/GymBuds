package com.example.gymbuds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.example.gymbuds.activities.BaseActivity
import com.example.gymbuds.firestore.FirestoreClass
import com.example.gymbuds.models.Bud
import com.example.gymbuds.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_bud_register.*

class BudRegisterActivity : BaseActivity(),View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bud_register)
    }

    fun createBud(){
        if(validateInput()){
            val email=edt_reg_email.text.toString().trim{it<' '}
            val password=edt_reg_password.text.toString().trim{it<' '}
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        val firebaseuser: FirebaseUser=it.result!!.user!!
                        val bud= Bud(
                            "",
                            edt_reg_Fname.text.toString().trim{it<' '},
                            edt_reg_Lname.text.toString().trim{it<' '},
                            "",0,edt_city.text.toString().trim{it<' '},
                            edt_state.text.toString().trim{it<' '},
                            "",
                            edt_where_you_train.text.toString().trim{it<' '},
                            getGender(),
                            "",
                            email,password

                        )
                    }
                }

        }





    }
    fun budSignInSuccess(){


    }

    fun validateInput():Boolean{

        return when{
            TextUtils.isEmpty(edt_reg_Fname.text.toString().trim{it<' '})->{
                return false
            }
            TextUtils.isEmpty(edt_reg_Lname.text.toString().trim{it<' '})->{
                return false
            }
            TextUtils.isEmpty(edt_city.text.toString().trim{it<' '})->{
                return false
            }
            TextUtils.isEmpty(edt_state.text.toString().trim{it<' '})->{
                return false
            }
            TextUtils.isEmpty(edt_reg_email.toString().trim{it<' '})->{
                return false
            }
            TextUtils.isEmpty(edt_where_you_train.text.toString().trim{it<' '})->{
                return false
            }
            else->{
                return true
            }


        }

    }
    fun getGender():String{
        var gender:String
        if(cbMale.isChecked){
            cbFemale.isChecked=false
            gender= Constants.MALE
            return gender
        }else{
            cbFemale.isChecked=true
            cbMale.isChecked=false
            gender=Constants.FEMALE
            return gender

        }

    }
    override fun onClick(p0: View?){
        if(p0!=null){
            when(p0.id){
                R.id.btn_register->{
                    createBud()
                }
            }
        }

    }
}