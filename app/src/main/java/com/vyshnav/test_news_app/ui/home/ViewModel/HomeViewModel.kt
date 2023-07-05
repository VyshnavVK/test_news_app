package com.vyshnav.test_news_app.ui.home.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vyshnav.test_news_app.api.retrofit.ApiState
import com.vyshnav.test_news_app.ui.home.repo.NewsHomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel(private var repository:NewsHomeRepository) : ViewModel(){
    val newsHomeState : MutableStateFlow<ApiState>  = MutableStateFlow(ApiState.Empty)

    fun getNewsData(source : String) = viewModelScope.launch {
        newsHomeState.value = ApiState.Loading
        try {
            val data = repository.getNewsData(source)
            data.collect{
                newsHomeState.value = ApiState.Success(it)
            }

        }catch (e:Exception){
            newsHomeState.value = ApiState.Failure(e)
        }
    }
}