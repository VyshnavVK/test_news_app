package com.vyshnav.test_news_app.api.retrofit

import com.vyshnav.test_news_app.api.data.newsListData
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET(AllApi.NEWS_LIST)
    suspend fun getDataList(@Query("sources") source : String,@Query("apiKey") apiKey : String): newsListData
}