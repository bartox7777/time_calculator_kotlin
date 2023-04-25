package edu.put.inf151860

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import java.util.Calendar
import java.util.Date

class daty : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daty)

        val buttonPlus: Button = findViewById(R.id.buttonPlus)

        val datePicker1: DatePicker = findViewById(R.id.datePicker1)
        val datePicker2: DatePicker = findViewById(R.id.datePicker2)

        val daysBetweenTextView = findViewById<TextView>(R.id.daysBetween)
        val workdaysTextView = findViewById<TextView>(R.id.workdays)

        var buttonPlusAction = false

        daysBetweenTextView.text =
            daysBetween(datePicker1.getDate(), datePicker2.getDate()).toString()

        datePicker1.setOnDateChangedListener() { _, _, _, _ ->
            if (!buttonPlusAction) {
                daysBetweenTextView.text =
                    daysBetween(datePicker1.getDate(), datePicker2.getDate()).toString()
                workdaysTextView.text = countWorkdays(
                    datePicker1.getDate(), datePicker2.getDate()
                ).toString()
            }
        }

        datePicker2.setOnDateChangedListener() { _, _, _, _ ->
            if (!buttonPlusAction) {
                daysBetweenTextView.text =
                    daysBetween(datePicker1.getDate(), datePicker2.getDate()).toString()
                workdaysTextView.text = countWorkdays(
                    datePicker1.getDate(), datePicker2.getDate()
                ).toString()
            }
        }

        buttonPlus.setOnClickListener() {
            buttonPlusAction = true
            val daysBetweenVal = daysBetweenTextView.text.toString().toLong()
            val calendar = Calendar.getInstance()
            calendar.time = datePicker1.getDate()
            calendar.add(Calendar.DAY_OF_MONTH, daysBetweenVal.toInt())
            datePicker2.updateDate(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            workdaysTextView.text = countWorkdays(
                datePicker1.getDate(), datePicker2.getDate()
            ).toString()
            buttonPlusAction = false
        }
    }

//    private fun countEaster(date: Date): Date {
//        val calendar = Calendar.getInstance()
//        calendar.time = date
//        val year = calendar.get(Calendar.YEAR)
//        val a = year % 19
//        val b = year / 100
//        val c = year % 100
//        val d = b / 4
//        val e = b % 4
//        val f = (b + 8) / 25
//        val g = (b - f + 1) / 3
//        val h = (19 * a + b - d - g + 15) % 30
//        val i = c / 4
//        val k = c % 4
//        val l = (32 + 2 * e + 2 * i - h - k) % 7
//        val m = (a + 11 * h + 22 * l) / 451
//        val p = (h + l - 7 * m + 114) % 31
//        val day = p + 1
//        val month = (h + l - 7 * m + 114) / 31
//        calendar.set(year, month, day)
//        return calendar.time
//    }

    private fun countWorkdays(date1: Date, date2: Date): Long {
        val fixedHolidays = arrayOf( // day, month
            "1 " + Calendar.JANUARY.toString(),
            "6 " + Calendar.JANUARY.toString(),
            "1 " + Calendar.MAY.toString(),
            "3 " + Calendar.MAY.toString(),
            "15 " + Calendar.AUGUST.toString(),
            "1 " + Calendar.NOVEMBER.toString(),
            "11 " + Calendar.NOVEMBER.toString(),
            "25 " + Calendar.DECEMBER.toString(),
            "26 " + Calendar.DECEMBER.toString()
        )

        val calendar1 = Calendar.getInstance()
        calendar1.time = date1

        val calendar2 = Calendar.getInstance()
        calendar2.time = date2

        var daysBetween = 0L
        if (calendar1.before(calendar2)) {
            while (calendar1.before(calendar2)) {
                if (calendar1.get(Calendar.DAY_OF_WEEK) !in arrayOf(
                        Calendar.SATURDAY, Calendar.SUNDAY
                    ) && !fixedHolidays.contains(
                        calendar1.get(Calendar.DAY_OF_MONTH).toString() + " " + calendar1.get(
                            Calendar.MONTH
                        ).toString()
                    )
                ) {
                    // print calendar1.time
                    ++daysBetween
                }
                calendar1.add(Calendar.DAY_OF_MONTH, 1)
            }
        } else if (calendar1.after(calendar2)) {
            while (calendar1.after(calendar2)) {
                if (calendar1.get(Calendar.DAY_OF_WEEK) !in arrayOf(
                        Calendar.SATURDAY, Calendar.SUNDAY
                    ) && !fixedHolidays.contains(
                        calendar1.get(Calendar.DAY_OF_MONTH).toString() + " " + calendar1.get(
                            Calendar.MONTH
                        ).toString()
                    )
                ) {
                    // print calendar1.time
                    --daysBetween
                }
                calendar1.add(Calendar.DAY_OF_MONTH, -1)
            }
        }
//
        return daysBetween
    }

    private fun daysBetween(date1: Date, date2: Date): Long {
        val difference = date2.time - date1.time
        return difference / (1000 * 60 * 60 * 24)
    }

    private fun DatePicker.getDate(): Date {
        val calendar = Calendar.getInstance()
        calendar.set(this.year, this.month, this.dayOfMonth)
        return calendar.time // or calendar.getTime() if you prefer
    }
}