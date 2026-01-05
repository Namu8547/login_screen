package com.example.first_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

import com.example.first_app.ui.LoginFragment



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val isUserLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        if(isUserLoggedIn){
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
            return

    }
        setContentView(R.layout.activity_main)

        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars = false  // white icons
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, LoginFragment())
            .commit()
//        else {
//            startActivity(Intent(this, MainActivity::class.java))
//    }




    //------ Align within SafeAre Avoid status bar space,navigation height, side insets -------
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fragment_container)) { v, insets ->
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

//
//        //-------------------------------------Align within SafeAre--------------------------------
//
//        var emailText = ""
//        val email : TextInputEditText = findViewById(R.id.entered_email)
//        email.addTextChangedListener {
//            val text = it.toString()
//            println(text)
//            emailText = text
//
//
//        }
//
//        var passwordText =""
//        val password : TextInputEditText = findViewById(R.id.entered_password)
//        password.addTextChangedListener{
//            val text = it.toString()
//            println(text)
//            passwordText = text
//
//        }
//
//
//        val  button : Button = findViewById(R.id.login_button)
//        button.setOnClickListener {
//            var text : String = ""
//                if(emailText.isEmpty()){
//                    text = "Enter email"
//                    showTopSnackbar(activity = this, message = text, backgroundColorRes = R.color.red, textColorRes = R.color.white)
//                }
//                else if (passwordText.isEmpty()){
//                    text = "Enter password"
//                    showTopSnackbar(activity = this, message = text, backgroundColorRes = R.color.red, textColorRes = R.color.white)
//                }
//                else {
//
//
////                    text = "Successfully logged in."
//////                  val duration = Toast.LENGTH_SHORT
//////                  Toast.makeText(this, text, duration).show()
////                    println("Logged In")
////
////                    showTopSnackbar(
////                        activity = this,
////                        message = text,
////                        backgroundColorRes = R.color.green,
////                        textColorRes = R.color.white
////                    )
//                    editor.putString("email", emailText)
//                    editor.putString("password", passwordText)
//                    editor.putBoolean("isLoggedIn", true )
//                    editor.apply()
//
//                    val getEmail = sharedPreferences.getString("email", "")
//                    val getPassword = sharedPreferences.getString("password", "")
//                    println("Email: $getEmail Password: $getPassword")
//
//
//                    startActivity(Intent(requireContext(), MainActivity::class.java))
//                    requireActivity().finish()
//
//
//                    val dialog =  AlertDialog.Builder(this)
//                        .setMessage(getString( R.string.success_msg))
//                        .setPositiveButton(getString(R.string.yes_button)) { _,_ -> }
//
//                        .create()
//
//                      dialog.setTitle("Confirmation")
//
//                    dialog.show()
//
////                    val bottomSheet = BottomSheetDialog(this,R.color.red)
////                    bottomSheet.show()
//                }
////
////            val snackbar1 = Snackbar.make(
////                findViewById(android.R.id.content),
////                text,
////                duration
////            )
////            snackbar1.view.backgroundTintList =
////                ColorStateList.valueOf(
////                    ContextCompat.getColor(this, R.color.blue)
////                )
////
////            snackbar1.view.findViewById<TextView>(
////                com.google.android.material.R.id.snackbar_text
////            ).setTextColor(Color.WHITE)
////
////
////            // Move Snackbar to TOP
////            val params = snackbar1.view.layoutParams as FrameLayout.LayoutParams
////            params.gravity = Gravity.TOP
////            params.topMargin = 100   // adjust for status bar
////            snackbar1.view.layoutParams = params
////            snackbar1.show()
//        }
    }
}