package com.vyshnav.test_news_app.api.retrofit

object AllApi {

    private external fun baseUrlFromJNI(boolean: Boolean): String

    const val BASE_URL = "https://newsapi.org/"

    private const val V2 = "v2/"

    const val NEWS_LIST = V2 + "top-headlines"

    const val API_KEY = "b6503bf834be44ec81fd6ce5b6cd28be"
}