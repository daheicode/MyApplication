package com.example.administrator.myapplication.douyin;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.VideoView;

import com.example.administrator.myapplication.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2018\6\5 0005.
 */

public class DouyinAdapter extends CommonAdapter<Integer> {
    public DouyinAdapter(Context context, int layoutId, List<Integer> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Integer i, int position) {
        VideoView videoView = (VideoView)holder.getView(R.id.video);
        videoView.setVideoURI(Uri.parse("android.resource://"+mContext.getPackageName()+"/"+ i));
    }
}
