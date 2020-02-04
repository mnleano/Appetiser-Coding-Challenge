package com.neds.appetisercodingchallenge.webService

import com.neds.appetisercodingchallenge.webService.model.SearchResponse
import io.reactivex.Observable

object ITunesApi : ITunesService {
    override fun search(): Observable<SearchResponse> {
        return accountService.search()
    }

    private fun getAPI(): ITunesService {
        return RetrofitServiceGenerator.createService(ITunesService::class.java)
    }

    private var accountService = getAPI()
}