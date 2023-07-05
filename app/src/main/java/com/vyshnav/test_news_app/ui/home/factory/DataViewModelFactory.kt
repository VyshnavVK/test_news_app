package com.vyshnav.test_news_app.ui.home.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vyshnav.test_news_app.ui.home.ViewModel.HomeViewModel
import com.vyshnav.test_news_app.ui.home.repo.NewsHomeRepository

class DataViewModelFactory(private val repository: NewsHomeRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }
}