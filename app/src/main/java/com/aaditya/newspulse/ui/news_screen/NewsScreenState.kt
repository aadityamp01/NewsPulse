package com.aaditya.newspulse.ui.news_screen

import com.aaditya.newspulse.domain.model.Article

//state - Any value tat can change during the usage of app
data class NewsScreenState(
    val isLoading: Boolean = false,
    val articles: List<Article> = emptyList(),
    val error: String? = null,
    val isSearchBarVisible: Boolean= false,
    val selectedArticle: Article? = null,
    val category: String = "General",
    val searchQuery: String = ""
)