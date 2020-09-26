package com.example.newsapplication.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.newsapplication.Const.BASE_URL;

public class NewsApiBuilder {
    public static NewsApiService service;

    public static NewsApiService getService() {
        if (service == null)
            service = builderRetrofit();
        return service;
    }

    private static NewsApiService builderRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsApiService.class);
    }

}
