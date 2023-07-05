package com.vyshnav.test_news_app.ui.home;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vyshnav.test_news_app.api.data.newsListData;
import com.vyshnav.test_news_app.databinding.NewsHomeItemBinding
import com.vyshnav.test_news_app.utils.utils

class homeAdapter(private val dataList: MutableList<newsListData.Articles>,var onClick: (newsListData.Articles, binding: NewsHomeItemBinding) -> Unit) : RecyclerView.Adapter<homeAdapter.ViewHolder>() {

        private var filteredDataList: MutableList<newsListData.Articles> = dataList

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = NewsHomeItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
            )
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val data = filteredDataList[position]
            holder.bind(data)
        }

        override fun getItemCount(): Int {
            return filteredDataList.size
        }

        fun filterData(query: String) {
            filteredDataList = if (query.isEmpty()) {
                dataList
            } else {
                dataList.filter { data ->
                        data.title!!.contains(query, ignoreCase = true) ||
                                data.title!!.contains(query, ignoreCase = true) ||
                                data.description!!.contains(query, ignoreCase = true)
                } as MutableList<newsListData.Articles>
            }
            notifyDataSetChanged()
        }

        inner class ViewHolder(private val binding: NewsHomeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(data: newsListData.Articles) {
                binding.tvTitle.text = data.title
                binding.tvSubtitle.text = data.description
                binding.tvDate.text = data.publishedAt?.let { utils().convertDate(it) }

                Glide.with(binding.root.context)
                        .load(data.urlToImage)
                        .into(binding.image)

                binding.root.setOnClickListener {
                    onClick.invoke(data,binding)
                }
            }
        }
    }


