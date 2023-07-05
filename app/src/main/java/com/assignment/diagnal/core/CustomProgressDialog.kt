package com.assignment.diagnal.core


import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.assignment.diagnal.R

class CustomProgressDialog(context: Context) : Dialog(context) {
    init {
        window?.decorView?.rootView?.setBackgroundResource(android.R.color.transparent)
        window?.decorView?.setOnApplyWindowInsetsListener { _, insets ->
            insets.consumeSystemWindowInsets()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view: View = LayoutInflater.from(context).inflate(
            R.layout.dialog_custom, null
        )
        setContentView(view)
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }
}