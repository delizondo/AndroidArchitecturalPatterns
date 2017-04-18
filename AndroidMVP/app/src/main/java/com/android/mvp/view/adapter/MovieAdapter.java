package com.android.mvp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mvp.R;
import com.omdb.model.bo.MovieObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by david on 4/18/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {


    private Context mContext;

    private List<MovieObject> mMovies;

    public MovieAdapter(Context context) {
        mMovies = new ArrayList<>();
        mContext = context;
    }

    public void setMovies(List<MovieObject> movies) {
        mMovies = movies;
        notifyDataSetChanged();
    }

    public void addMovies(List<MovieObject> list) {
        mMovies.addAll(list);
        notifyDataSetChanged();
    }

    public void reset() {
        mMovies.clear();
        notifyDataSetChanged();
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_view, parent, false);
        return new MovieHolder(v);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        MovieObject movieObject = mMovies.get(position);
        holder.mName.setText(mContext.getString(R.string.movie_name, movieObject.getTitle()));
        holder.mYear.setText(mContext.getString(R.string.movie_year, movieObject.getYear()));
        Picasso.with(mContext).load(movieObject.getPoster())
                .resizeDimen(R.dimen.image_width, R.dimen.image_height)
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public static class MovieHolder extends RecyclerView.ViewHolder {


        ImageView mImageView;
        TextView mName;
        TextView mYear;

        public MovieHolder(View v) {
            super(v);
            mImageView = (ImageView) v.findViewById(R.id.poster);
            mName = (TextView) v.findViewById(R.id.name);
            mYear = (TextView) v.findViewById(R.id.year);
        }
    }
}
