package com.vyshnav.test_news_app.api.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private const val TIME_OUT: Long = 120

    private val gson = GsonBuilder().setLenient().create()

    private val okHttpClient = OkHttpClient.Builder()
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val resp = chain.proceed(chain.request())
            if (resp.code() == 200) {
                try {
                    val myJson = resp.peekBody(2048).string()
                    println(myJson)
                } catch (e: Exception) {
                    println("Error parse json from intercept..............")
                }
            } else {
                println(resp)
            }
            resp
        }.build()

    val retrofit: RetrofitInterface by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(AllApi.BASE_URL)
            .client(okHttpClient)
            .build().create(RetrofitInterface::class.java)
    }

}