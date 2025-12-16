package com.example.first_app.utils


import android.app.Activity
import android.content.res.ColorStateList
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

fun showTopSnackbar(
    activity: Activity,
    message: String,
    backgroundColorRes: Int,
    textColorRes: Int,
    duration: Int = Snackbar.LENGTH_SHORT
) {
    val rootView = activity.findViewById<View>(android.R.id.content)


    val snackbar: Snackbar = Snackbar.make(
        rootView,
        message,
        duration
    )

    // Background color
    snackbar.view.backgroundTintList =
        ColorStateList.valueOf(
            ContextCompat.getColor(activity, backgroundColorRes)
        )

    // Text color
    snackbar.view.findViewById<TextView>(
        com.google.android.material.R.id.snackbar_text
    ).setTextColor(
        ContextCompat.getColor(activity, textColorRes)
    )

    // Position at top (edge-to-edge safe)
    val params = snackbar.view.layoutParams as FrameLayout.LayoutParams
    params.gravity = Gravity.TOP

    params.topMargin =
        ViewCompat.getRootWindowInsets(rootView)
            ?.getInsets(WindowInsetsCompat.Type.systemBars())
            ?.top ?: 0

    snackbar.view.layoutParams = params

    snackbar.show()
}
