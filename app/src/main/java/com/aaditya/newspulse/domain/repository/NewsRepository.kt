package com.aaditya.newspulse.domain.repository

import com.aaditya.newspulse.domain.model.Article
import com.aaditya.newspulse.util.Resource

interface NewsRepository {

    suspend fun getTopHeadlines(
        category: String
    ): Resource<List<Article>>


    suspend fun searchForNews(
        query: String
    ): Resource<List<Article>>
}