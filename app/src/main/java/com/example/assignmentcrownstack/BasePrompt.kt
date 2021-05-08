package com.example.assignmentcrownstack

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import com.example.assignmentcrownstack.utils.Common


open class BasePrompt : DialogFragment() {

    private var width: Float = 0.8f
    private var height: Float = ConstraintLayout.LayoutParams.WRAP_CONTENT.toFloat()


    fun setPromptDimensions(width: Float, height: Float) {
        this.width = width
        this.height = height
    }

    override fun onStart() {
        super.onStart()
        isCancelable = false
        Common.calculateDialogDimensions(activity, dialog, width, height)
    }

    fun measureAgain() {
        Common.calculateDialogDimensions(activity, dialog, width, height)
    }
}