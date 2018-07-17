package com.example.administrator.myapplication.coordinatorlayout;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import com.example.administrator.myapplication.R;


public class Act0203 extends AppCompatActivity {

    private Toolbar tb;
    private CollapsingToolbarLayout ctb;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_0203);

        tb = (Toolbar) this.findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        ctb = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ctb.setTitle("哆啦A梦");

        rv = (RecyclerView) findViewById(R.id.recyclerView);
        rv.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(new RecyclerViewAdapter(Act0203.this));
    }

}
