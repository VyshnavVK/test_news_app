package com.vyshnav.test_news_app.utils

import java.text.SimpleDateFormat
import java.util.Locale

class utils {
    fun convertDate(dateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("'Updated: 'MMM dd',' HH:mm", Locale.getDefault())

        val date = inputFormat.parse(dateString)
        val formattedDate = outputFormat.format(date)

        return formattedDate
    }
}