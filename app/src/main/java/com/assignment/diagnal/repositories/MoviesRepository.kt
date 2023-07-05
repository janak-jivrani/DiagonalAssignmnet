package com.assignment.diagnal.repositories

import com.assignment.diagnal.api.LocalApis
import com.assignment.diagnal.models.MoviesResponse

class MoviesRepository constructor(private val localApis: LocalApis) {

    fun getMoviesList(currentPage: Int): MoviesResponse {
        return localApis.getJsonResponse(currentPage)
    }

}