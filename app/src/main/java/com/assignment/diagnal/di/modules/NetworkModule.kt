package com.assignment.diagnal.di.modules

import android.content.Context
import com.assignment.diagnal.api.LocalApis
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun provideLocalApi(context: Context): LocalApis {
        return LocalApis(context)
    }
}