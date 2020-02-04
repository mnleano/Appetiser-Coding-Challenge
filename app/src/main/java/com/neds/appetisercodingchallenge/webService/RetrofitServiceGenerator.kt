package com.neds.appetisercodingchallenge.webService

import io.reactivex.schedulers.Schedulers
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitServiceGenerator {

    private val rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
    private val builder = Retrofit.Builder()
        .baseUrl("https://itunes.apple.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(rxAdapter)

    private var retrofit = builder.build()

    private val okHttpClientBuilder = OkHttpClient.Builder()

    private const val requestTimeOut: Long = 60
    init {
//        okHttpClientBuilder.addInterceptor { chain ->
//            val eventBus = EventBus.getDefault()
//            eventBus.post(HttpEvent(false))
//            val requestBuilder = chain.request().newBuilder()
//                .addHeader("Content-Type", "application/json")
//
//            val request = requestBuilder.build()
//
//            val response: Response = chain.proceed(request)

        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 500
        dispatcher.maxRequestsPerHost = 50

        okHttpClientBuilder.connectTimeout(requestTimeOut, TimeUnit.SECONDS)
            .readTimeout(requestTimeOut, TimeUnit.SECONDS)
            .writeTimeout(requestTimeOut, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .dispatcher(dispatcher)
        builder.client(okHttpClientBuilder.build())
        retrofit = builder.build()
    }

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }
}