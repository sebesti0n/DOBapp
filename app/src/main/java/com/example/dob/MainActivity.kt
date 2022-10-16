package com.example.dob

import android.app.DatePickerDialog
import android.icu.util.Calendar
//import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
//import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvselecteddate:TextView?=null
    private var ageinmin:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn1: Button=findViewById(R.id.btnForDate)
        tvselecteddate= findViewById(R.id.tvselecteddate)
        ageinmin=findViewById(R.id.tvageinminutes)
        btn1.setOnClickListener{
            datepickered()
        }
    }
//    @RequiresApi(Build.VERSION_CODES.N)
   private fun datepickered(){
        val calend=java.util.Calendar.getInstance()
        val year=calend.get(Calendar.YEAR)
        val month=calend.get(Calendar.MONTH)
        val day=calend.get(Calendar.DAY_OF_MONTH)
      val dpd=  DatePickerDialog(this,
//            DatePickerDialog.OnDateSetListener
            { view, selectedyear,  selectedmonth, selecteddayOfMonth->
        Toast.makeText(this,"year was $selectedyear,month was ${1+selectedmonth},the day of month was $selecteddayOfMonth,",Toast.LENGTH_LONG).show()

                val selecteddate="$selecteddayOfMonth/${1+selectedmonth}/$selectedyear"
            tvselecteddate?.setText(selecteddate)
                tvselecteddate?.text=selecteddate.toString()
                val sdf=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val date=sdf.parse(selecteddate)
              date?.let {
                  val selecteddateinminutes=date.time/60000
                  val currentdate=sdf.parse(sdf.format(System.currentTimeMillis()))
                  currentdate?.let {
                      val differenceinminutes=(currentdate.time/60000)-selecteddateinminutes

                      ageinmin?.text=differenceinminutes.toString()
                  }

              }

            },
            year,
            month,
            day
        )
    dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
    dpd.show()
    }

}