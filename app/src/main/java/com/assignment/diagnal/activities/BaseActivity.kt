package com.assignment.diagnal.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.appcompat.app.AppCompatActivity
import com.assignment.diagnal.core.BetterActivityResult
import com.assignment.diagnal.core.CustomProgressDialog

open class BaseActivity : AppCompatActivity(), View.OnClickListener {
    var mActivity: Context? = null
    lateinit var mDialog: CustomProgressDialog
    private lateinit var activityLauncher: BetterActivityResult<Intent, ActivityResult>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = this
        mDialog = CustomProgressDialog(this)
        activityLauncher = BetterActivityResult.registerActivityForResult(this)
    }

    fun setUpClicks(vararg views: View) {
        for (view in views) {
            view.setOnClickListener(this)
        }
    }

    override fun onClick(v: View) {}
}