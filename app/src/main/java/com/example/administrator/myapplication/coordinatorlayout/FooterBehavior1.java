package com.example.administrator.myapplication.coordinatorlayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;

public class FooterBehavior1 extends CoordinatorLayout.Behavior<View> {

    private int direct;

    public FooterBehavior1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        final View child0=child;
        Log.e("dire",direct+"");
        Log.e("dire0",child.getHeight()+"");
        if (dy > 0 && direct < 0 || dy < 0 && direct > 0) {
            child.animate().cancel();
            direct = 0;
        }
        direct += dy;
        if (direct > child.getHeight() && child.getVisibility() == View.VISIBLE) {
            hide(child);
//            child.setVisibility(View.INVISIBLE);
        } else if (direct<0 && child.getVisibility() == View.INVISIBLE) {
            show(child);
        }
    }

    // 捕获对内部View的fling事件，如果return true则表示拦截掉内部View的事件
    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed) {
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }

    private void hide(final View view) {
        ViewPropertyAnimator animator = view.animate().translationY(view.getHeight()).setInterpolator(new FastOutSlowInInterpolator()).setDuration(200);
        animator.setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(View.INVISIBLE);
            }
        });
        animator.start();
    }

    private void show(final View view) {
        ViewPropertyAnimator animator = view.animate().translationY(0).setInterpolator(new FastOutSlowInInterpolator()).setDuration(200);
        animator.setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(View.VISIBLE);
            }
        });
        animator.start();
    }
}