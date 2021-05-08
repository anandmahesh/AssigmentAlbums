package com.example.assignmentcrownstack

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ProgressPrompt : BasePrompt() {

    lateinit var mContext: Context

    companion object {
        fun getInstance(): ProgressPrompt {
            return ProgressPrompt()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.prompt_process_loader, container, false)
    }
}