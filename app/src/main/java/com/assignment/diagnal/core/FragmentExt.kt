package com.assignment.diagnal.core

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

fun Fragment.showToast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(activity, msg, duration).show()
}

inline fun <reified T : ViewModel> Fragment.getViewModelFromFactory(vmFactory: ViewModelProvider.Factory): T {
    return ViewModelProvider(this, vmFactory)[T::class.java]
}