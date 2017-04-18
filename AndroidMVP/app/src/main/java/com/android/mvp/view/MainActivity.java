package com.android.mvp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.android.mvp.R;
import com.android.mvp.presenter.SearchPresenter;
import com.android.mvp.presenter.SearchPresenterView;
import com.android.mvp.view.adapter.MarginDecoration;
import com.android.mvp.view.adapter.MovieAdapter;
import com.omdb.model.bo.MovieObject;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchPresenterView, View.OnClickListener {

    public static final String TAG = MainActivity.class.getName();

    private EditText mSearch;

    private RecyclerView mRecyclerView;

    private MovieAdapter mAdapter;

    private SearchPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new SearchPresenter(this);
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
    public void addMovies(List<MovieObject> movies) {
        mAdapter.addMovies(movies);
    }

    @Override
    public void resetAdapter() {
        mAdapter.reset();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                mPresenter.searchMovies(mSearch.getText().toString());
                break;
        }
    }

    public class MovieScrollListener extends RecyclerView.OnScrollListener {
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

            if (!mPresenter.isLoading()) {
                if (total > 0)
                    if ((total - 1) == lastVisibleItemCount) {
                        if (mPresenter.getCurrentPage() < mPresenter.getTotalPages()) {
                            mPresenter.addPage();
                            mPresenter.setLoading(true);
                            mPresenter.requestMovies();
                        }
                    }
            }
        }
    }
}
