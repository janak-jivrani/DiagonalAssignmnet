package com.assignment.diagnal.di.modules

import com.assignment.diagnal.repositories.MoviesRepository
import com.assignment.diagnal.viewmodels.MoviesViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun provideMoviesViewModel(moviesRepository: MoviesRepository): MoviesViewModel {
        return MoviesViewModel(moviesRepository)
    }
}