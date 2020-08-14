package com.example.popularmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Locale;

import static com.example.popularmovies.Constants.BASE_IMAGE_URL;
import static com.example.popularmovies.Constants.DEFAULT_POSITION;
import static com.example.popularmovies.Constants.IMAGE_SIZE;
import static com.example.popularmovies.Constants.MOVIE_POSITION;

public class MovieDetails extends AppCompatActivity {
    int position;

    ImageView movieImageIV;
    TextView movieTitleTV;
    TextView ratingTV;
    TextView releaseDateTV;
    TextView plotTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        getMoviePosition();
        setUpUI();
        getMovieDir();
    }

    public void getMoviePosition(){
        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        position = intent.getIntExtra(MOVIE_POSITION, DEFAULT_POSITION);

        if(position==DEFAULT_POSITION){
            closeOnError();
        }

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void setUpUI(){
        movieTitleTV=findViewById(R.id.movieTitle);
        movieImageIV=findViewById(R.id.movieImage);
        ratingTV=findViewById(R.id.rating);
        releaseDateTV=findViewById(R.id.released);
        plotTV=findViewById(R.id.plot);
    }

    private void getMovieDir(){
        ArrayList<Result> movieList;

        try
        {
            FileInputStream fis = new FileInputStream(new File(getString(R.string.pathToFile)));
            ObjectInputStream ois = new ObjectInputStream(fis);
            movieList = (ArrayList) ois.readObject();

            ois.close();
            fis.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
            return;
        }
        catch (ClassNotFoundException c)
        {
            System.out.println(getString(R.string.class_not_found));
            c.printStackTrace();
            return;
        }

        setValues(movieList);
    }



    private void setValues(ArrayList<Result> movieList){
        Result movie=movieList.get(position);

        String movieImageString=movie.getBackdropPath();
        Picasso.get().load(BASE_IMAGE_URL+IMAGE_SIZE+movieImageString).into(movieImageIV);

        String titleString=movie.getOriginalTitle();
        movieTitleTV.setText(titleString);

        Double voteAverage=movie.getVoteAverage();
        String voteAverageString=String.format(Locale.US,getString(R.string.format_double), voteAverage);

        ratingTV.setText(voteAverageString);

        String releaseDateString=movie.getReleaseDate();
        releaseDateTV.setText(releaseDateString);

        String plotString=movie.getOverview();
        plotTV.setText(plotString);

    }

}