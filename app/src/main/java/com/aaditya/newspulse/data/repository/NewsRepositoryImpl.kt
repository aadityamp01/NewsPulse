package com.aaditya.newspulse.data.repository

import com.aaditya.newspulse.data.remote.NewsApi
import com.aaditya.newspulse.domain.model.Article
import com.aaditya.newspulse.domain.repository.NewsRepository
import com.aaditya.newspulse.util.Resource

class NewsRepositoryImpl (
    private val newsApi: NewsApi
): NewsRepository {

    override suspend fun getTopHeadlines(category: String): Resource<List<Article>> {
        return try {
            val response = newsApi.getBreakingNews(category = category)
            Resource.Success(data = response.articles)
        } catch (e: Exception) {
            Resource.Error(message = "Failed to fetch news ${e.message}")
        }
    }

    override suspend fun searchForNews(query: String): Resource<List<Article>> {
        return try {
            val response = newsApi.searchForNews(query = query)
            Resource.Success(data = response.articles)
        } catch (e: Exception) {
            Resource.Error(message = "Failed to fetch news ${e.message}")
        }
    }
}