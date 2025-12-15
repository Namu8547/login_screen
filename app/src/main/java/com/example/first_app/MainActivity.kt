package com.example.first_app

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import androidx.core.widget.addTextChangedListener


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //------ Align within SafeAre Avoid status bar space,navigation height, side insets -------
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main))
        { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //-------------------------------------Align within SafeAre--------------------------------

        val email : TextInputEditText = findViewById(R.id.entered_email)
        email.addTextChangedListener {
            val text = it.toString()
            println(text)

        }


        val password : TextInputEditText = findViewById(R.id.entered_password)
        password.addTextChangedListener{
            val text = it.toString()
            println(text)
        }


        val  button : Button = findViewById(R.id.login_button)
        button.setOnClickListener { println("Logged In") }

    }
}