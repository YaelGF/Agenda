package com.example.agenda

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Second : AppCompatActivity() {
    @SuppressLint("WrongViewCast", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val actionBar = supportActionBar

        actionBar!!.title="Second"

        actionBar.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras
        val r = extras?.getString("nombre")?:"desconocido"
        var result = findViewById<TextView>(R.id.result)
        result.setText("$r fue registrado con exito")
    }
}