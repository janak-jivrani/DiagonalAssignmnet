package com.assignment.diagnal.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.assignment.diagnal.core.CustomProgressDialog

open class BaseFragment : Fragment(), View.OnClickListener {
    var mActivity: Context? = null

    lateinit var mDialog: CustomProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = requireActivity()
        mDialog = CustomProgressDialog(requireContext())
    }

    fun setUpClicks(vararg views: View) {
        for (view in views) {
            view.setOnClickListener(this)
        }
    }

    override fun onClick(v: View) {}
}