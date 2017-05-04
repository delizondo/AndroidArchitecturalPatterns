package com.android.mvvm.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;

import com.android.mvvm.R;
import com.android.mvvm.databinding.ActivityMainBinding;
import com.android.mvvm.view.adapter.MarginDecoration;
import com.android.mvvm.viewModel.SearchViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binder = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binder.recyclerView.addItemDecoration(new MarginDecoration(this));
        binder.recyclerView.setHasFixedSize(true);
        binder.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        binder.setSearch(new SearchViewModel(this, binder.recyclerView));
    }
}
