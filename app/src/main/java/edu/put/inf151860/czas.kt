package edu.put.inf151860

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import kotlin.math.abs

class czas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_czas)

        val (h1, m1, s1) = arrayOf(findViewById<EditText>(R.id.h1), findViewById<EditText>(R.id.m1), findViewById<EditText>(R.id.s1))
        val (h2, m2, s2) = arrayOf(findViewById<EditText>(R.id.h2), findViewById<EditText>(R.id.m2), findViewById<EditText>(R.id.s2))

        val buttonPlus = findViewById<Button>(R.id.button_plus)
        buttonPlus.setOnClickListener {
            val (h, m, s) = arrayOf(h1.text.toString().toInt(), m1.text.toString().toInt(), s1.text.toString().toInt())
            val (h_, m_, s_) = arrayOf(h2.text.toString().toInt(), m2.text.toString().toInt(), s2.text.toString().toInt())
            val (h__, m__, s__) = arrayOf(h + h_, m + m_, s + s_)
            val finalH = h__ + (m__ + s__ / 60) / 60
            val finalM = (m__ + s__ / 60) % 60
            val finalS = s__ % 60
            h1.setText(finalH.toString()); m1.setText(finalM.toString()); s1.setText(finalS.toString())
            h2.setText("0"); m2.setText("0"); s2.setText("0")
        }

        val buttonMinus = findViewById<Button>(R.id.button_minus)
        buttonMinus.setOnClickListener {
            val (h, m, s) = arrayOf(h1.text.toString().toInt(), m1.text.toString().toInt(), s1.text.toString().toInt())
            val (h_, m_, s_) = arrayOf(h2.text.toString().toInt(), m2.text.toString().toInt(), s2.text.toString().toInt())
            val secondsSum1 = h * 3600 + m * 60 + s
            val secondsSum2 = h_ * 3600 + m_ * 60 + s_
            val secondsDiff = secondsSum1 - secondsSum2
            val finalH = secondsDiff / 3600
            val finalM = abs((secondsDiff % 3600) / 60)
            val finalS = abs(secondsDiff % 60)
            h1.setText(finalH.toString()); m1.setText(finalM.toString()); s1.setText(finalS.toString())
            h2.setText("0"); m2.setText("0"); s2.setText("0")
        }

        val buttonAC = findViewById<Button>(R.id.button_ac)
        buttonAC.setOnClickListener {
            h1.setText("0"); m1.setText("0"); s1.setText("0")
            h2.setText("0"); m2.setText("0"); s2.setText("0")
        }
    }
}