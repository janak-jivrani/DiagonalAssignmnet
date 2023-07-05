package com.assignment.diagnal.core

import android.app.Activity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

fun Activity.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

inline fun <reified T : ViewModel> AppCompatActivity.getViewModelFromFactory(vmFactory: ViewModelProvider.Factory): T {
    return ViewModelProvider(this, vmFactory)[T::class.java]
}