package edu.put.inf151860

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonCzas = findViewById<Button>(R.id.button_czas)
        buttonCzas.setOnClickListener {
            val intent = Intent(this, czas::class.java)
            startActivity(intent)
        }

        val buttonDaty = findViewById<Button>(R.id.button_daty)
        buttonDaty.setOnClickListener {
            val intent = Intent(this, daty::class.java)
            startActivity(intent)
        }

    }
}