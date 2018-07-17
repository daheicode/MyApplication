package com.example.administrator.myapplication.douyin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.VideoView;

import com.example.administrator.myapplication.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018\6\5 0005.
 */

public class DouYinActivity extends AppCompatActivity {
    private RecyclerView recycler;
    private ArrayList<Integer> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_douyin);
        initViews();
        initDatas();
    }

    private void initDatas() {
        datas = new ArrayList<>();
        datas.add(R.raw.video_1);
        datas.add(R.raw.video_2);
        datas.add(R.raw.video_1);
        datas.add(R.raw.video_2);
        douyinLayoutManager manager = new douyinLayoutManager(this);
        recycler.setLayoutManager(manager);
        recycler.setAdapter(new DouyinAdapter(this,R.layout.item_douyin, datas));
        manager.setOnPagerSelectListener(new douyinLayoutManager.OnPagerSelect() {
            @Override
            public void pagerSelect() {
                View view = recycler.getChildAt(0);
                VideoView video = (VideoView) view.findViewById(R.id.video);
                video.start();
            }
        });
    }

    private void initViews() {
        recycler = (RecyclerView) findViewById(R.id.recycler);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(datas!=null){
            datas.clear();
            datas=null;
        }
    }
}
