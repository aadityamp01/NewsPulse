package com.aaditya.newspulse.ui.news_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aaditya.newspulse.domain.repository.NewsRepository
import com.aaditya.newspulse.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsScreenViewModel @Inject constructor(
    // getting data from repository to viewModel
    private val newsRepository: NewsRepository
): ViewModel() {

    var state by mutableStateOf(NewsScreenState())

/*
    init{
        getNewsArticles(category = "general")
    }
*/

    private var searchJob: Job? = null

    fun onEvent(event: NewsScreenEvent) {
        when (event) {
            is NewsScreenEvent.OnCategoryChanged -> {
                state = state.copy(category = event.category)
                getNewsArticles(category = state.category)
            }

            is NewsScreenEvent.OnNewsCardClicked -> {
                state = state.copy(selectedArticle = event.article)
            }

            NewsScreenEvent.OnSearchIconClicked -> {
                state = state.copy(
                    isSearchBarVisible = true,
                    articles = emptyList()
                )
            }

            NewsScreenEvent.OnCloseIconClicked -> {
                state = state.copy(isSearchBarVisible = false)
                getNewsArticles(category = state.category)
            }

            is NewsScreenEvent.OnSearchQueryChanged -> {
                state = state.copy(searchQuery = event.searchQuery)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(1000L)
                    searchForNews(query = state.searchQuery)
                }
            }
        }
    }


    private fun getNewsArticles(category: String) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            when (val result = newsRepository.getTopHeadlines(category = category)) {
                is Resource.Success -> {
                    state = state.copy(
                        articles = result.data ?: emptyList(),
                        isLoading = false,
                        error = null
                    )
                }

                is Resource.Error -> {
                    state = state.copy(
                        articles = emptyList(),
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }

    private fun searchForNews(query: String) {
        if (query.isEmpty()) {
            return
        }
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            when (val result = newsRepository.searchForNews(query = query)) {
                is Resource.Success -> {
                    state = state.copy(
                        articles = result.data ?: emptyList(),
                        isLoading = false,
                        error = null
                    )
                }

                is Resource.Error -> {
                    state = state.copy(
                        articles = emptyList(),
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }

}