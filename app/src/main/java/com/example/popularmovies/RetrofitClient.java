package com.example.popularmovies;

import java.util.logging.Level;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;

    private static OkHttpClient loggingInterceptor=createLoggingInteceptor();

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(NetworkUtils.createMoviesURL())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(loggingInterceptor)
                    .build();
        }
        return retrofit;
    }

    public static OkHttpClient createLoggingInteceptor(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        return client;
    };
}

