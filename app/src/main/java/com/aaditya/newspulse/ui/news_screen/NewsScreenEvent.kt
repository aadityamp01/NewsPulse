package com.aaditya.newspulse.ui.news_screen

import com.aaditya.newspulse.domain.model.Article

//Event - All the possible actions user can do
sealed class NewsScreenEvent {
    data class OnNewsCardClicked(var article: Article) : NewsScreenEvent()
    data class OnCategoryChanged(var category: String) : NewsScreenEvent()
    data class OnSearchQueryChanged(var searchQuery: String) : NewsScreenEvent()
    object OnSearchIconClicked: NewsScreenEvent()
    object OnCloseIconClicked: NewsScreenEvent()
}