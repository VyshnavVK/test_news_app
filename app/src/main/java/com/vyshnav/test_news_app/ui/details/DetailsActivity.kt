package com.vyshnav.test_news_app.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import android.view.ViewTreeObserver
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import com.bumptech.glide.Glide
import com.vyshnav.test_news_app.R
import com.vyshnav.test_news_app.databinding.ActivityDetailsBinding
import com.vyshnav.test_news_app.utils.utils

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            customToolbar.backArrow.visibility = View.VISIBLE
            customToolbar.backText.visibility = View.VISIBLE
            customToolbar.toolbar.title= "Details"

            customToolbar.backText.setOnClickListener { finish() }

        }

        intent.let {
            binding.tvTitle.text = it.getStringExtra("title")
            binding.tvSubtitle.text = it.getStringExtra("subtitle")
            binding.tvDate.text = it.getStringExtra("date")?.let { utils().convertDate(it) }

            Glide.with(binding.root.context)
                .load(it.getStringExtra("image"))
                .into(binding.image)
        }
    }

}