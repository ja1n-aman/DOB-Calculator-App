package com.example.dobcalc


import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast

import java.lang.NullPointerException
import java.text.SimpleDateFormat

import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvDate : TextView? = null
    private var tvTime : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonDatePicker : Button = findViewById(R.id.buttonDatePicker)
        tvDate = findViewById(R.id.displayDate)
        tvTime = findViewById(R.id.displayTime)

        buttonDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }
    fun clickDatePicker(){


        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{view,selyear,selmonth,selday->
                Toast.makeText( this,
                    "Selected Date : $selday/${selmonth+1}/$selyear" , Toast.LENGTH_LONG).show()

                val selDate ="$selday/${selmonth+1}/$selyear"

                tvDate?.text = selDate
                val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
                val theDate = sdf.parse(selDate)
                theDate?.let {
                    val selectedTimeInHours = theDate.time/3600000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let{
                        val currentTimeInHours = currentDate.time/3600000
                        val difference = currentTimeInHours-selectedTimeInHours-24

                        val ageInMin=(difference).toString()
                        tvTime?.text = ageInMin
                    }
                }
                                              },year,month,day)

        dpd.datePicker.maxDate=System.currentTimeMillis() - 86400000
        dpd.show()

    }
}

