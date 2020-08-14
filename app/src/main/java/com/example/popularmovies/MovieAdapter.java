package com.example.popularmovies;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.popularmovies.Constants.BASE_IMAGE_URL;
import static com.example.popularmovies.Constants.IMAGE_SIZE;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    Result[] moviesList;
    private LayoutInflater mInflater;
    private OnMovieListener onMovieListener;


    Context context;

    public interface OnMovieListener{
        void onMovieClick(int position);
    }

    MovieAdapter(Context context, Result[] moviesList, OnMovieListener onMovieListener){
        this.context=context;
        this.mInflater = LayoutInflater.from(context);
        this.moviesList=moviesList;
        this.onMovieListener=onMovieListener;
    }


    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.movie_image_item, parent, false);
        return new MovieViewHolder(view, onMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, final int position) {
        Result movie=moviesList[position];
        String imageAsString=movie.getBackdropPath();

        Picasso.get().load(BASE_IMAGE_URL+IMAGE_SIZE+imageAsString).into(holder.movieThumbnail);

    }


    public static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            ImageView movieThumbnail;
            OnMovieListener onMovieListener;
            public MovieViewHolder(View itemView, OnMovieListener onMovieListener){
                super(itemView);
                movieThumbnail=itemView.findViewById(R.id.movie_thumbnail);
                this.onMovieListener=onMovieListener;

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if (onMovieListener != null){
                onMovieListener.onMovieClick(getAdapterPosition());
            }
       }
    }


    @Override
    public int getItemCount() {
        if(moviesList!=null) {
           int size= moviesList.length;
           return size;
        }
        return 0;
    }
}


