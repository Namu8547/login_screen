package com.example.first_app
//import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
//import android.util.Log
//import android.view.Gravity
import android.widget.Button
//import android.widget.FrameLayout
//import android.widget.TextView
//import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
//import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import androidx.core.widget.addTextChangedListener
import com.example.first_app.utils.showTopSnackbar
//import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //------ Align within SafeAre Avoid status bar space,navigation height, side insets -------
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val cutout = insets.getInsets(WindowInsetsCompat.Type.displayCutout())
            v.setPadding(
                maxOf(systemBars.left, cutout.left) ,
                maxOf(systemBars.top, cutout.top),
                maxOf(systemBars.right, cutout.right) ,
                maxOf(systemBars.bottom, cutout.bottom)
            )
            insets
        }

        //-------------------------------------Align within SafeAre--------------------------------

        var emailText = ""
        val email : TextInputEditText = findViewById(R.id.entered_email)
        email.addTextChangedListener {
            val text = it.toString()
            println(text)
            emailText = text

        }

        var passwordText =""
        val password : TextInputEditText = findViewById(R.id.entered_password)
        password.addTextChangedListener{
            val text = it.toString()
            println(text)
            passwordText = text
        }


        val  button : Button = findViewById(R.id.login_button)
        button.setOnClickListener {
            var text : String = ""
                if(emailText.isEmpty()){
                    text = "Enter email"
                    showTopSnackbar(activity = this, message = text, backgroundColorRes = R.color.red, textColorRes = R.color.white)
                }
                else if (passwordText.isEmpty()){
                    text = "Enter password"
                    showTopSnackbar(activity = this, message = text, backgroundColorRes = R.color.red, textColorRes = R.color.white)
                }
                else {


                    text = "Successfully logged in."
//            val duration = Toast.LENGTH_SHORT
//            Toast.makeText(this, text, duration).show()
                    println("Logged In")

                    showTopSnackbar(
                        activity = this,
                        message = text,
                        backgroundColorRes = R.color.green,
                        textColorRes = R.color.white
                    )
                }
//
//            val snackbar1 = Snackbar.make(
//                findViewById(android.R.id.content),
//                text,
//                duration
//            )
//            snackbar1.view.backgroundTintList =
//                ColorStateList.valueOf(
//                    ContextCompat.getColor(this, R.color.blue)
//                )
//
//            snackbar1.view.findViewById<TextView>(
//                com.google.android.material.R.id.snackbar_text
//            ).setTextColor(Color.WHITE)
//
//
//            // Move Snackbar to TOP
//            val params = snackbar1.view.layoutParams as FrameLayout.LayoutParams
//            params.gravity = Gravity.TOP
//            params.topMargin = 100   // adjust for status bar
//            snackbar1.view.layoutParams = params
//            snackbar1.show()
        }
    }
}