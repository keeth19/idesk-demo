package com.example.ideskdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.os.Bundle;

import com.example.ideskdemo.util.Utilize;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.Drawer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Activity mActivity = this;

        Toolbar mToolBar = findViewById(R.id.login_toolbar);
        setSupportActionBar(mToolBar);

        AccountHeader headerResult = Utilize.profileHeader(savedInstanceState, mActivity);
        Drawer result = Utilize.drawerBody(mToolBar, savedInstanceState, mActivity);
    }
}