package com.example.administrator.myapplication.xuanfuchong;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.myapplication.R;

import java.nio.channels.FileLock;
import java.util.Random;

/**
 * Created by Administrator on 2018\6\20 0020.
 */

public  class xuanfuService extends Service{

    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;
    private Button button;
    private ImageView imagev;
    private int[] arr={R.mipmap.bk,R.mipmap.ic_launcher};
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                Random random=new Random();
                imagev.setImageResource(arr[1]);
//                handler.sendEmptyMessageAtTime(0,2000);
            }
        }
    };
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        showxuanfu();
        return super.onStartCommand(intent, flags, startId);
    }

    private void showxuanfu() {
        if(Settings.canDrawOverlays(this)){
            windowManager = (WindowManager)getSystemService(WINDOW_SERVICE);
           /* button = new Button(getApplicationContext());
           button.setText("xuanfubutton");
           *//*button.setWidth(600);
           button.setHeight(200);*//*
            button.setBackgroundColor(Color.GREEN);
            button.setOnTouchListener(new xuanfuTouchListen());*/
            imagev = new ImageView(getApplicationContext());
            imagev.setImageResource(R.mipmap.bk);
            imagev.setOnTouchListener(new xuanfuTouchListen());
            layoutParams = new WindowManager.LayoutParams();
           if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
               layoutParams.type=WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY;
           }else{
               layoutParams.type=WindowManager.LayoutParams.TYPE_PHONE;
           }
           layoutParams.width=500;
           layoutParams.height=500;
           layoutParams.x=300;
           layoutParams.y=300;

//           windowManager.addView(button, layoutParams);
            windowManager.addView(imagev,layoutParams);
//            handler.sendEmptyMessageAtTime(0,2000);
        }
    }

    class xuanfuTouchListen implements View.OnTouchListener{

        private float y;
        private float x;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    y = event.getRawY();
                    x = event.getRawX();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float nowY=event.getRawY();
                    float nowX=event.getRawX();
                    int moveY=(int)(nowY-y);
                    int moveX=(int)(nowX-x);
                    x=nowX;
                    y=nowY;
                    layoutParams.x=layoutParams.x+moveX;
                    layoutParams.y=layoutParams.y+moveY;
//                    windowManager.updateViewLayout(button,layoutParams);
                    windowManager.updateViewLayout(imagev,layoutParams);
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
            return false;
        }
    }
}
