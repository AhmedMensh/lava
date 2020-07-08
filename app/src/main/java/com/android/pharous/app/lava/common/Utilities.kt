package com.android.pharous.app.lava.common

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.widget.AutoCompleteTextView
import android.widget.DatePicker
import android.widget.TextView
import com.android.pharous.app.lava.R
import org.w3c.dom.Text
import java.util.*

 fun pickDateDialog(textView: TextView) {

    // Get Current Date
    val c = Calendar.getInstance()
    val mYear = c[Calendar.YEAR]
    val mMonth = c[Calendar.MONTH]
    val mDay = c[Calendar.DAY_OF_MONTH]
    val datePickerDialog = DatePickerDialog(
        textView.context,
        OnDateSetListener { view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->
            textView.text = "$year-${(monthOfYear + 1)}-$dayOfMonth"

        },
        mYear, mMonth, mDay
    )
//    datePickerDialog.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ok", datePickerDialog)
//    datePickerDialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Canc", datePickerDialog)
//    datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
    datePickerDialog.show()
}
