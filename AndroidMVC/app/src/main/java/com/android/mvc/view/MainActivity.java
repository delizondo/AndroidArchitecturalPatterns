package com.android.mvc.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.mvc.MVCApplication;
import com.android.mvc.R;
import com.android.mvc.view.adapter.MarginDecoration;
import com.android.mvc.view.adapter.MovieAdapter;
import com.omdb.model.bo.MovieFeed;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = MainActivity.class.getName();

    private EditText mSearch;

    private RecyclerView mRecyclerView;

    private MovieAdapter mAdapter;

    private int mCurrentPage = 1;

    private int mTotalPages;

    private boolean mIsLoading;

    private String mMovieTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button1).setOnClickListener(this);
        mSearch = (EditText) findViewById(R.id.search);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mRecyclerView.addItemDecoration(new MarginDecoration(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.addOnScrollListener(new MovieScrollListener());
        mAdapter = new MovieAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                searchMovies();
                break;
        }
    }


    private void searchMovies() {
        mAdapter.reset();
        mMovieTitle = mSearch.getText().toString();
        mCurrentPage = 1;
        requestMovies();
    }


    private void requestMovies() {
        Call<MovieFeed> call = MVCApplication.getInstance().getAPI().getImages(mMovieTitle, mCurrentPage);
        Log.d(TAG, call.request().toString());
        call.enqueue(new Callback<MovieFeed>() {
            @Override
            public void onResponse(Call<MovieFeed> call, Response<MovieFeed> response) {
                Log.d(TAG, "success");
                mIsLoading = false;
                if (response.body().getMovies() != null) {
                    mAdapter.addMovies(response.body().getMovies());
                    mTotalPages = response.body().getTotalResults();
                }
            }

            @Override
            public void onFailure(Call<MovieFeed> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }


    private class MovieScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

            int total = layoutManager.getItemCount();
            int lastVisibleItemCount = layoutManager.findLastVisibleItemPosition();

            if (!mIsLoading) {
                if (total > 0)
                    if ((total - 1) == lastVisibleItemCount) {
                        if (mCurrentPage < mTotalPages) {
                            mCurrentPage++;
                            mIsLoading = true;
                            requestMovies();
                        }
                    }
            }
        }
    }
}
