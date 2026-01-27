package com.example.first_app
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.first_app.ui.LoginFragment
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.Manifest
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging


class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAuth : FirebaseAuth;
    private var database: DatabaseReference = FirebaseDatabase.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "default_channel",
                "Default Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    1001
                )
            }
        }
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("FCM_TEST", "Token: ${task.result}")
                } else {
                    Log.e("FCM_TEST", "Token fetch failed", task.exception)
                }
            }






        Thread.sleep(3000)
        installSplashScreen()
        enableEdgeToEdge()


        firebaseAuth = FirebaseAuth.getInstance()

        val currentUser = firebaseAuth.currentUser

        if(currentUser != null){
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
            return
        }

//        val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//
//        val isUserLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
//        if(isUserLoggedIn == false){
//            startActivity(Intent(this, HomeActivity::class.java))
//            finish()
//            return
//    }
        
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