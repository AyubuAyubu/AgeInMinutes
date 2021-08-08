package com.bazuma.coding

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)
        // This is used to align the xml view to this class
        setContentView(R.layout.activity_main)
        /*This is implemented by android studio it self when we select the Basic Activity while creating the project.*/
        //setSupportActionBar(toolbar)

    /**
     *View is Something that can be displayed in a screen
     *we get the view then use the view in the method clickDatePicker(view)
     */
        btnDatePicker.setOnClickListener {view->
            clickDatePicker(view)
            }
    }
    //datePickerDialogue is a class used to help us to open date Picker
    /**
     * The function to show the DatePicker Dialog.
     */
    fun clickDatePicker(View: View){
        /**
         * This Gets a calendar using the default time zone and locale.
         * The calender returned is based on the current time
         * in the default time zone with the default.
         */
        val myCalender=Calendar.getInstance()
        val year=myCalender.get(Calendar.YEAR)
        val month=myCalender.get(Calendar.MONTH)
        val day=myCalender.get(Calendar.DAY_OF_MONTH)
        /**
         * Creates a new date picker dialog for the specified date using the parent
         * context's default date picker dialog theme.
         */
        val dpd=DatePickerDialog(this,DatePickerDialog.OnDateSetListener
        { view, selectedYear, selectedMonth, selectedDayOfMonth ->

            /**
             * The listener used to indicate the user has finished selecting a date.
             */

            /**
             *Here the selected date is set into format i.e : day/Month/Year
             * And the month is counted in java is 0 to 11 so we need to add +1 so it can be as selected.
             * */
            val selectDate="$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            tvSelectedDate.setText(selectDate)


            /**
             * Here we have taken an instance of Date Formatter as it will format our
             * selected date in the format which we pass it as an parameter and Locale.
             * Here I have passed the format as dd/MM/yyyy.
             */
            val sdf=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            //convert it from a selectDate from string to format of Date object
            val theDate=sdf.parse(selectDate)
            /** Here we have get the time in milliSeconds from Date object
             * And as we know the formula as milliseconds can be converted to second by dividing it by 1000.
             * And the seconds can be converted to minutes by dividing it by 60.
             * So now in the selected date into minutes.
             */
            val selectedDateToMinutes = theDate!!.time / 60000

            // Here we have parsed the current date with the Date Formatter which is used above.
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            // Current date in to minutes.
            val currentDateToMinutes = currentDate!!.time / 60000

            /**
             * Now to get the difference into minutes.
             * We will subtract the selectedDateToMinutes from currentDateToMinutes.
             * Which will provide the difference in minutes.
             */
            val differenceInMinutes = currentDateToMinutes - selectedDateToMinutes

            // Set the difference in minutes to textview to show the user.
            tvSelectedDateInMinutes.setText(differenceInMinutes.toString())
        },
            year,
            month,
            day)
        dpd.datePicker.setMaxDate(Date().time-86400000)
        dpd.show()


    }
}
