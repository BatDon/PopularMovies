package com.example.popularmovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("/3/movie/popular")
    Call<MoviePojo> getAllMovies(@Query("api_key") String api_key);

    @GET("/3/movie/top_rated")
    Call<MoviePojo> getSortedPopularMovies(@Query("api_key") String api_key);

}
