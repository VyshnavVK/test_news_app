package com.vyshnav.test_news_app.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.vyshnav.test_news_app.api.data.newsListData
import com.vyshnav.test_news_app.api.retrofit.ApiState
import com.vyshnav.test_news_app.databinding.ActivityMainBinding
import com.vyshnav.test_news_app.ui.details.DetailsActivity
import com.vyshnav.test_news_app.ui.home.ViewModel.HomeViewModel
import com.vyshnav.test_news_app.ui.home.factory.DataViewModelFactory
import com.vyshnav.test_news_app.ui.home.repo.NewsHomeRepository

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            DataViewModelFactory(NewsHomeRepository())
        )[HomeViewModel::class.java]

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        lifecycleScope.launchWhenCreated {
            viewModel.getNewsData("techcrunch")
            viewModel.newsHomeState.collect {
                when (it) {
                    is ApiState.Loading -> {
                        binding.progress.visibility = View.VISIBLE
                    }

                    is ApiState.Failure -> {
                        binding.progress.visibility = View.GONE
                        it.e.printStackTrace()
                    }

                    is ApiState.Success -> {
                        binding.progress.visibility = View.GONE

                        try {
                            val dataList = it.data as newsListData
                            val adapter = homeAdapter(dataList.articles) { item,itemView ->


                                val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                                val options = ActivityOptionsCompat.makeCustomAnimation(this@MainActivity, android.R.anim.fade_in, android.R.anim.fade_out)
                                intent.putExtra("title",item.title)
                                intent.putExtra("subtitle",item.description)
                                intent.putExtra("image",item.urlToImage)
                                intent.putExtra("date",item.publishedAt)
                                startActivity(intent, options.toBundle())


                            }
                            binding.rvNews.adapter = adapter

                            binding.etSearch.doAfterTextChanged {search->
                                adapter.filterData(search.toString())
                            }


                        }catch (e:Exception){
                            e.printStackTrace()
                        }




                    }

                    is ApiState.Empty -> {
                        binding.progress.visibility = View.GONE
                        println("Empty...")
                    }
                }
            }
        }
    }
}