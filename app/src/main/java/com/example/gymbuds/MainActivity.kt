package com.example.gymbuds

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txt_register.setOnClickListener{

            intent= Intent(this@MainActivity,BudRegisterActivity::class.java)
            startActivity(intent)


        }
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



}