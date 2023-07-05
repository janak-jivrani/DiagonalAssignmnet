package com.assignment.diagnal.api

import android.content.Context
import android.util.Log
import com.assignment.diagnal.core.Constant
import com.assignment.diagnal.models.MoviesResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

const val TAG = "LocalApis"

class LocalApis(val context: Context) {

    fun getJsonResponse(currentPage: Int): MoviesResponse {
        lateinit var jsonString: String
        try {
            jsonString =
                context.assets.open("${Constant.JSON_NAME}$currentPage${Constant.JSON_EXT}")
                    .bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            Log.e(TAG, ioException.message!!)
        }

        val resType = object : TypeToken<MoviesResponse>() {}.type
        return Gson().fromJson(jsonString, resType)
    }

}