package com.example.popularmovies;

import android.net.Uri;
import android.util.Log;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public final class NetworkUtils {

    public static String createMoviesURL(){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("api.themoviedb.org")
                .appendPath("");
        builder.build();
        String popularMoviesURL= builder.build().toString();
        Log.i("NetworkUtils","url= "+popularMoviesURL);
        return popularMoviesURL;
    }
}
