package com.example.gymbuds.activities

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.gymbuds.R
import com.example.gymbuds.firestore.FirestoreClass
import com.example.gymbuds.models.Bud
import com.example.gymbuds.utils.Constants
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_bud_register.*

class BudRegisterActivity : BaseActivity(),View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bud_register)
        rbtn_register.setOnClickListener(this)
        cbMale.setOnClickListener{
            cbFemale.isChecked=false
        }
        cbFemale.setOnClickListener{
            cbMale.isChecked=false
        }

        val adapter= ArrayAdapter(this,
            R.layout.list_item,resources.getStringArray(R.array.us_states))
        stateSpinner.setAdapter(adapter)
    }

    fun createBud(){
        showProgressDialog("Please Wait")
        if(validateInput()){
            val email=edt_reg_email.text.toString().trim{it<' '}
            val password=edt_reg_password.text.toString().trim{it<' '}
            FirebaseApp.initializeApp(this)
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        val firebaseuser: FirebaseUser=it.result!!.user!!
                        val bud= Bud(firebaseuser.uid,
                            edt_reg_Fname.text.toString().trim{it<' '},
                            edt_reg_Lname.text.toString().trim{it<' '},
                            "",0,citySpinner.text.toString().trim{it<' '},
                            stateSpinner.text.toString().trim{it<' '},
                            "", edt_where_you_train.text.toString().trim{it<' '},
                            getGender(),
                            "",
                            email,password

                        )
                        FirestoreClass().registerBud(this@BudRegisterActivity,bud)
                    }
                }

        }





    }
    fun budRegisterSuccess(){
        hideProgressDialog()
        Toast.makeText(this,resources.getString(R.string.register_success),Toast.LENGTH_SHORT).show()

        FirebaseAuth.getInstance().signOut()
        finish()


    }

    fun validateInput():Boolean{

        return when{
            TextUtils.isEmpty(edt_reg_Fname.text.toString().trim{it<' '})->{
                return false
            }
            TextUtils.isEmpty(edt_reg_Lname.text.toString().trim{it<' '})->{
                return false
            }
            TextUtils.isEmpty(citySpinner.text.toString().trim{it<' '})->{
                return false
            }
            TextUtils.isEmpty(stateSpinner.text.toString().trim{it<' '})->{
                return false
            }
            TextUtils.isEmpty(edt_reg_email.toString().trim{it<' '})->{
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
                R.id.rbtn_register ->{
                    createBud()
                }
            }
        }

    }
}