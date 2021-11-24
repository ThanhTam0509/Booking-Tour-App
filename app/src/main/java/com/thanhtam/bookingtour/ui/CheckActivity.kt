package com.thanhtam.bookingtour.ui

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.michalsvec.singlerowcalendar.calendar.CalendarChangesObserver
import com.michalsvec.singlerowcalendar.calendar.CalendarViewManager
import com.michalsvec.singlerowcalendar.calendar.SingleRowCalendarAdapter
import com.michalsvec.singlerowcalendar.selection.CalendarSelectionManager
import com.michalsvec.singlerowcalendar.utils.DateUtils
import com.thanhtam.bookingtour.R
import kotlinx.android.synthetic.main.activity_check.*
import kotlinx.android.synthetic.main.first_special_calendar_item.view.*
import java.util.*

class CheckActivity : AppCompatActivity() {

    private val calendar = Calendar.getInstance()
    private var currentMonth = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check)
        checkOut()

        calendar.time = Date()
        currentMonth = calendar[Calendar.MONTH]

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.WHITE
        }

        val myCalendarViewManager = object : CalendarViewManager {
            override fun setCalendarViewResourceId(
                position: Int,
                date: Date,
                isSelected: Boolean
            ): Int {
                // set date to calendar according to position where we are
                val cal = Calendar.getInstance()
                cal.time = date
                // if item is selected we return this layout items
                // in this example monday, wednesday and friday will have special item views and other days
                // will be using basic item view
                return if (isSelected)
                    when (cal[Calendar.DAY_OF_WEEK]) {
                        Calendar.MONDAY -> R.layout.selected_calendar_item
                        Calendar.TUESDAY -> R.layout.selected_calendar_item
                        Calendar.WEDNESDAY -> R.layout.selected_calendar_item
                        Calendar.THURSDAY -> R.layout.selected_calendar_item
                        Calendar.FRIDAY -> R.layout.selected_calendar_item
                        Calendar.SATURDAY -> R.layout.selected_calendar_item
                        Calendar.SUNDAY -> R.layout.selected_calendar_item
                        else -> R.layout.calendar_item
                    }
                else
                // here we return items which are not selected
                    when (cal[Calendar.DAY_OF_WEEK]) {
                        Calendar.MONDAY -> R.layout.calendar_item
                        Calendar.TUESDAY -> R.layout.calendar_item
                        Calendar.WEDNESDAY -> R.layout.calendar_item
                        Calendar.THURSDAY -> R.layout.calendar_item
                        Calendar.FRIDAY -> R.layout.calendar_item
                        Calendar.SATURDAY -> R.layout.calendar_item
                        Calendar.SUNDAY -> R.layout.calendar_item
                        else -> R.layout.calendar_item
                    }

                // NOTE: if we don't want to do it this way, we can simply change color of background
                // in bindDataToCalendarView method
            }

            override fun bindDataToCalendarView(
                holder: SingleRowCalendarAdapter.CalendarViewHolder,
                date: Date,
                position: Int,
                isSelected: Boolean
            ) {
                // using this method we can bind data to calendar view
                // good practice is if all views in layout have same IDs in all item views
                holder.itemView.tv_date_calendar_item.text = DateUtils.getDayNumber(date)
                holder.itemView.tv_day_calendar_item.text = DateUtils.getDay3LettersName(date)

            }
        }

        // using calendar changes observer we can track changes in calendar
        val myCalendarChangesObserver = object : CalendarChangesObserver {
            // you can override more methods, in this example we need only this one
            override fun whenSelectionChanged(isSelected: Boolean, position: Int, date: Date) {
                tvDate.text = "${DateUtils.getMonthName(date)}, ${DateUtils.getDayNumber(date)} "
                tvDay.text = DateUtils.getDayName(date)
                super.whenSelectionChanged(isSelected, position, date)
            }
        }

        // selection manager is responsible for managing selection
        val mySelectionManager = object : CalendarSelectionManager {
            override fun canBeItemSelected(position: Int, date: Date): Boolean {
                // set date to calendar according to position
                val cal = Calendar.getInstance()
                cal.time = date
                //in this example sunday and saturday can't be selected, other item can be selected
                return when (cal[Calendar.DAY_OF_WEEK]) {
                    Calendar.SATURDAY -> true
                    Calendar.SUNDAY -> true
                    else -> true
                }
            }
        }

        // here we init our calendar, also you can set more properties if you need them
        val singleRowCalendar = main_single_row_calendar.apply {
            calendarViewManager = myCalendarViewManager
            calendarChangesObserver = myCalendarChangesObserver
            calendarSelectionManager = mySelectionManager
            setDates(getFutureDatesOfCurrentMonth())
            init()
        }

        btnRight.setOnClickListener {
            singleRowCalendar.setDates(getDatesOfNextMonth())
        }

        btnLeft.setOnClickListener {
            singleRowCalendar.setDates(getDatesOfPreviousMonth())
        }
    }

    private fun getDatesOfNextMonth(): List<Date> {
        currentMonth++ // + because we want next month
        if (currentMonth == 12) {
            // we will switch to january of next year, when we reach last month of year
            calendar.set(Calendar.YEAR, calendar[Calendar.YEAR] + 1)
            currentMonth = 0 // 0 == january
        }
        return getDates(mutableListOf())
    }

    private fun getDatesOfPreviousMonth(): List<Date> {
        currentMonth-- // - because we want previous month
        if (currentMonth == -1) {
            // we will switch to december of previous year, when we reach first month of year
            calendar.set(Calendar.YEAR, calendar[Calendar.YEAR] - 1)
            currentMonth = 11 // 11 == december
        }
        return getDates(mutableListOf())
    }

    private fun getFutureDatesOfCurrentMonth(): List<Date> {
        // get all next dates of current month
        currentMonth = calendar[Calendar.MONTH]
        return getDates(mutableListOf())
    }


    private fun getDates(list: MutableList<Date>): List<Date> {
        // load dates of whole month
        calendar.set(Calendar.MONTH, currentMonth)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        list.add(calendar.time)
        while (currentMonth == calendar[Calendar.MONTH]) {
            calendar.add(Calendar.DATE, +1)
            if (calendar[Calendar.MONTH] == currentMonth)
                list.add(calendar.time)
        }
        calendar.add(Calendar.DATE, -1)
        return list
    }

    private fun checkOut() {
        btnTime1.setOnClickListener {
            val intent = Intent(this, CardActivity::class.java)
            startActivity(intent)
        }
    }
}