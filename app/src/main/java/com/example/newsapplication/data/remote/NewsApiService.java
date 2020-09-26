package com.example.newsapplication.data.remote;
import com.example.newsapplication.models.NewsModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {
    @GET("v2/top-headlines")
    Call<NewsModel> getTopNews(
            @Query("apiKey") String apiKey,
            @Query("country") String language
    );
}
