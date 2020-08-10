package com.example.ideskdemo;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ideskdemo.database.DBUtils;
import com.example.ideskdemo.object.User;
import com.example.ideskdemo.util.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashActivity extends AppCompatActivity {
    Context mcontext;
    DatabaseReference rootRef;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mcontext = SplashActivity.this;

        if(!Utils.getSingIn(this)){
            startActivity(new Intent(SplashActivity.this,StartActivity.class));
        }else{
            DBUtils.deleteUser(this);
            insertUser();
        }
    }

    public void insertUser(){
        rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference assaultRef = rootRef.child("Users");
        ValueEventListener eventListener = new ValueEventListener() {
            @SuppressLint("NewApi")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    DBUtils.insert_RegisterUser_to_table(user,SplashActivity.this);
                }

                startActivity(new Intent(SplashActivity.this,MainActivity.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        assaultRef.addListenerForSingleValueEvent(eventListener);
    }

}