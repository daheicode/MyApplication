package com.example.administrator.myapplication.douyin;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2018\6\5 0005.
 */

public class douyinLayoutManager extends LinearLayoutManager {

    private PagerSnapHelper mSnap;
    private RecyclerView recyclerView;
    private OnPagerSelect listener;

    public douyinLayoutManager(Context context) {
        super(context);
        init();
    }

    public douyinLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        init();
    }

    public douyinLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    /*
    初始化PagerSnapHelper类
    实现recyclerview的单页滑动
     */
    private void init() {
        mSnap = new PagerSnapHelper();
    }

    @Override
    public void onAttachedToWindow(RecyclerView view) {
        super.onAttachedToWindow(view);
        //绑定recyclerview
        mSnap.attachToRecyclerView(view);
        this.recyclerView=view;
    }


    /**
     * 滑动状态的改变
     * 缓慢拖拽-> SCROLL_STATE_DRAGGING
     * 快速滚动-> SCROLL_STATE_SETTLING
     * 空闲状态-> SCROLL_STATE_IDLE
     * 确定它的选中状态
     */
    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        switch (state){
            case RecyclerView.SCROLL_STATE_DRAGGING:
                break;
                case RecyclerView.SCROLL_STATE_SETTLING:
                    break;
                    case RecyclerView.SCROLL_STATE_IDLE:
                        //当前处于选中状态
                        listener.pagerSelect();
                        break;
                        default:
                            break;
        }
    }

    public void setOnPagerSelectListener(OnPagerSelect listener){
        if(listener!=null){
            this.listener=listener;
        }
    }

    public interface OnPagerSelect{
        void pagerSelect();
    }
}
