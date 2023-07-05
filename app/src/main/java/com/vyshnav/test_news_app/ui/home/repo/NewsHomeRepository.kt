package com.vyshnav.test_news_app.ui.home.repo

import com.vyshnav.test_news_app.api.data.newsListData
import com.vyshnav.test_news_app.api.retrofit.AllApi
import com.vyshnav.test_news_app.api.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class NewsHomeRepository {

    fun getNewsData(source : String): Flow<newsListData> = flow {
        val response = RetrofitClient.retrofit.getDataList(source,AllApi.API_KEY)
        emit(response)
    }.flowOn(Dispatchers.IO)

}