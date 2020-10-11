package com.id6130205356.ass8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_insert.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InsertActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)
    }
    fun addData(v:View){
        val serv: EmployeeAPI = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EmployeeAPI::class.java)
        val selectID = input_gender.checkedRadioButtonId
        var checked = findViewById<RadioButton>(selectID)
        serv.insertEmp(
            input_name.text.toString(),
            checked.text.toString(),
            input_email.text.toString(),
            input_salary.text.toString().toInt()
        ).enqueue(object :Callback<Employee>{
            override fun onResponse(call: Call<Employee>, response: Response<Employee>) {
                if (response.isSuccessful()){
                    Toast.makeText(applicationContext,"Successfully Insrted",Toast.LENGTH_LONG).show()
                    finish()
                }else{
                    Toast.makeText(applicationContext,"Error",Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Employee>, t: Throwable) {
                Toast.makeText(applicationContext,"Error onFailure "+t.message,Toast.LENGTH_LONG).show()
            }
        })
    }
    fun cancle(v: View){
        finish()
    }
}