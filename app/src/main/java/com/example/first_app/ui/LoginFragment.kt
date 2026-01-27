package com.example.first_app.ui

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.Toast

import androidx.core.widget.addTextChangedListener
import com.example.first_app.HomeActivity
import com.example.first_app.MainActivity
import com.example.first_app.R
import com.example.first_app.utils.showTopSnackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.database


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var auth : FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()


        //-------------------------------------Align within SafeAre--------------------------------

        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        var emailText = ""
        val email : TextInputEditText = view.findViewById(R.id.entered_email)
        email.addTextChangedListener {
            val text = it.toString()
            println(text)
            emailText = text


        }

        var passwordText =""
        val password : TextInputEditText = view.findViewById(R.id.entered_password)
        password.addTextChangedListener{
            val text = it.toString()
            println(text)
            passwordText = text

        }

        val  loginButton : Button = view.findViewById(R.id.login_button)
        loginButton.setOnClickListener {
            var text : String = ""
            if(emailText.isEmpty()){
                text = "Enter email"
                showTopSnackbar(activity = requireActivity(), message = text, backgroundColorRes = R.color.red, textColorRes = R.color.white)
                return@setOnClickListener
            }
            else if (passwordText.isEmpty()){
                text = "Enter password"
                showTopSnackbar(activity = requireActivity(), message = text, backgroundColorRes = R.color.red, textColorRes = R.color.white)
                return@setOnClickListener
            }
            else{
                auth.signInWithEmailAndPassword(emailText, passwordText).addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(context, "Login successfully", Toast.LENGTH_LONG).show()
                        startActivity(Intent(view.context, HomeActivity::class.java))
                        requireActivity().finish()
                        val database = Firebase.database
                        val myRef = database.getReference("message")

                        myRef.setValue("Hello, World!")
                    }
                    else if(task.exception?.message == "The password is invalid or the user does not have a password."){
                    Toast.makeText(context, "Invalid password", Toast.LENGTH_LONG).show()
}
                    else {
                        Toast.makeText(context, "Failed to login", Toast.LENGTH_LONG).show()
                    }

                }
            }

        }

        val  registerButton : Button = view.findViewById(R.id.register_button)
        registerButton.setOnClickListener {

            var text : String = ""
            if(emailText.isEmpty()){
                text = "Enter email"
                showTopSnackbar(activity = requireActivity(), message = text, backgroundColorRes = R.color.red, textColorRes = R.color.white)
                return@setOnClickListener
            }
            else if (passwordText.isEmpty()){
                text = "Enter password"
                showTopSnackbar(activity = requireActivity(), message = text, backgroundColorRes = R.color.red, textColorRes = R.color.white)
                return@setOnClickListener
            }

            else {


//                    text = "Successfully logged in."
////                  val duration = Toast.LENGTH_SHORT
////                  Toast.makeText(this, text, duration).show()
//                    println("Logged In")
//
//                    showTopSnackbar(
//                        activity = this,
//                        message = text,
//                        backgroundColorRes = R.color.green,
//                        textColorRes = R.color.white
//                    )
                auth.createUserWithEmailAndPassword(emailText, passwordText).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(context, "Registration successful! Please login", Toast.LENGTH_LONG).show()
                        // if the user created intent to login activity

                    }
                    else if (task.exception?.message == "The email address is already in use by another account."){
                        Toast.makeText(context, "Email is already registered, please login", Toast.LENGTH_LONG).show()
                }
                    else {
                        // Registration failed
                        Toast.makeText(context, "Registration failed!!" + " Please try again later", Toast.LENGTH_LONG).show()
                    }}
//                editor.putString("email", emailText)
//                editor.putString("password", passwordText)
//                editor.putBoolean("isLoggedIn", true )
//                editor.apply()
//
//                val getEmail = sharedPreferences.getString("email", "")
//                val getPassword = sharedPreferences.getString("password", "")
//                println("Email: $getEmail Password: $getPassword")


//                startActivity(Intent(view.context, HomeActivity::class.java))
//                requireActivity().finish()

// .              ------------------Success Dialog -------------------
//                val dialog =  AlertDialog.Builder(view.context)
//                    .setMessage(getString( R.string.success_msg))
//                    .setPositiveButton(getString(R.string.yes_button)) { _,_ -> }
//
//                    .create()
//
//                dialog.setTitle("Confirmation")
//
//                dialog.show()

//                *****************************************************

//                    val bottomSheet = BottomSheetDialog(this,R.color.red)
//                    bottomSheet.show()
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