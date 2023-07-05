package com.aaditya.newspulse.data.remote
import com.aaditya.newspulse.domain.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    //https://newsapi.org/v2/top-headlines?country=us&apiKey=2b3350c2e130493a94f280d8c05ca388

    @GET("top-headlines")
    suspend fun getBreakingNews(
        @Query("category") category: String,
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

    @GET("everything")
    suspend fun searchForNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

    companion object {
        const val API_KEY = "3b065ed816b945abaf13056ac0fd39f5"
        const val BASE_URL = "https://newsapi.org/v2/"
    }
}