package com.example.assignmentcrownstack.utils

import android.app.Activity
import android.app.Dialog
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.constraintlayout.widget.ConstraintLayout

class Common {

    companion object{
        fun calculateDialogDimensions(
            activity: Activity?,
            dialog: Dialog?,
            width: Float,
            height: Float
        ) {
            if (activity == null || dialog?.window == null) {
                return
            }

            val displayMetrics = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
            val displayWidth = displayMetrics.widthPixels
            val displayHeight = displayMetrics.heightPixels
            val layoutParams = WindowManager.LayoutParams()
            layoutParams.copyFrom(dialog.window!!.attributes)
            // Set the alert dialog window width and height
// Set alert dialog width equal to screen width 90%
// int dialogWindowWidth = (int) (displayWidth * 0.9f);
// Set alert dialog height equal to screen height 90%
// int dialogWindowHeight = (int) (displayHeight * 0.9f);
// Set the width and height for the layout parameters
// This will bet the width and height of alert dialog
            if (width != ConstraintLayout.LayoutParams.WRAP_CONTENT.toFloat() && width != ConstraintLayout.LayoutParams.MATCH_PARENT.toFloat()) { // Set alert dialog width equal to screen width as ratio using width like 0.7f to 70%
                layoutParams.width = (displayWidth * width).toInt()
            } else {
                layoutParams.width = width.toInt()
            }
            if (height != ConstraintLayout.LayoutParams.WRAP_CONTENT.toFloat() && height != ConstraintLayout.LayoutParams.MATCH_PARENT.toFloat()) { // Set alert dialog height equal to screen height as ratio using height like 0.7f to 70%
                layoutParams.height = (displayHeight * height).toInt()
            } else {
                layoutParams.height = height.toInt()
            }
            // Apply the newly created layout parameters to the alert dialog window
            dialog.window!!.attributes = layoutParams
            dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }
}