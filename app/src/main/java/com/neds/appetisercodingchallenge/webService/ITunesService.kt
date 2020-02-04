package com.neds.appetisercodingchallenge.webService

import com.neds.appetisercodingchallenge.webService.model.SearchResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ITunesService {
    @GET("search?term=star&amp;country=au&amp;media=movie&amp;all")
    fun search(): Observable<SearchResponse>
}