package com.example.administrator.myapplication.xuanfuchong;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.myapplication.R;

/**
 * Created by Administrator on 2018\6\20 0020.
 * 悬浮窗必须在android 23以上
 */

public class xuanfuActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuanfu);
        button = (Button) findViewById(R.id.but);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(!Settings.canDrawOverlays(this)){
            Toast.makeText(xuanfuActivity.this,"当前无权限，请授权",Toast.LENGTH_LONG).show();
            startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:"+getPackageName())),0);
        }else{
            startService(new Intent(xuanfuActivity.this,xuanfuService.class));
        }
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        if(resultCode==0){
            if(!Settings.canDrawOverlays(this)){
                Toast.makeText(this, "权限申请失败", Toast.LENGTH_SHORT).show();
                }else{
                Toast.makeText(this, "权限申请成功", Toast.LENGTH_SHORT).show();
                startService(new Intent(xuanfuActivity.this,xuanfuService.class));
            }
        }
    }
}
