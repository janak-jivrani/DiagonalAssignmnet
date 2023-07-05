package com.assignment.diagnal.di

import com.assignment.diagnal.activities.MainActivity
import com.assignment.diagnal.di.modules.ContextModule
import com.assignment.diagnal.di.modules.NetworkModule
import com.assignment.diagnal.di.modules.RepositoryModule
import com.assignment.diagnal.di.modules.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ContextModule::class, ViewModelModule::class, RepositoryModule::class, NetworkModule::class]
)
interface AppComponent {
    // Classes that can be injected by this Component
    fun inject(application: MoviesApplication)
    fun inject(mainActivity: MainActivity)
}