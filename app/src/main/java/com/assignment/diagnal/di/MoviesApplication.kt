package com.assignment.diagnal.di

import android.app.Application
import com.assignment.diagnal.di.modules.ContextModule

class MoviesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        try {
            component = DaggerAppComponent.builder().contextModule(ContextModule(this)).build()
            component.inject(this)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        lateinit var component: AppComponent
    }
}