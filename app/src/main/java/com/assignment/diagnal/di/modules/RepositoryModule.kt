package com.assignment.diagnal.di.modules

import com.assignment.diagnal.api.LocalApis
import com.assignment.diagnal.repositories.MoviesRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideMoviesRepository(localApis: LocalApis): MoviesRepository {
        return MoviesRepository(localApis)
    }
}