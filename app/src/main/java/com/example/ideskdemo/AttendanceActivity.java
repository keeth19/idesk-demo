package com.example.ideskdemo;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ideskdemo.R;
import com.example.ideskdemo.database.DBUtils;
import com.example.ideskdemo.object.Attends;
import com.example.ideskdemo.object.User;
import com.example.ideskdemo.util.Utilize;
import com.example.ideskdemo.util.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.net.InternetDomainName;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.Drawer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class AttendanceActivity extends AppCompatActivity implements  View.OnClickListener{
    private ImageButton leftArrow;
    private ImageButton rightArrow;

    private TextView studentName;

    private ArrayList<String> studentNamesList = new ArrayList<>();

    String userName;
    String userGrade ;
    String userClass ;
    String status ;
    String school;
    String currentUser;

    private static  int index = 0;

    ArrayList<User> userArrayList = new ArrayList<>();
    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        Activity mActivity = this;

        Toolbar mToolBar = findViewById(R.id.toolbar_dashboard);
        setSupportActionBar(mToolBar);

        AccountHeader headerResult  = Utilize.profileHeader(savedInstanceState, mActivity);
        Drawer result               = Utilize.drawerBody(mToolBar, savedInstanceState, mActivity);

        leftArrow = findViewById(R.id.leftArrow);
        rightArrow = findViewById(R.id.rightArrow);

        studentName = findViewById(R.id.studentName);

        final TextView name = findViewById(R.id.name);
        final TextView grade = findViewById(R.id.grade);

        Button buttonPending = findViewById(R.id.buttonPending);
        Button buttonAbsent = findViewById(R.id.buttonAbsent);
        Button buttonPresent = findViewById(R.id.buttonPresent);

        buttonPresent.setOnClickListener(this);
        buttonAbsent.setOnClickListener(this);
        buttonPresent.setOnClickListener(this);

        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("Users");
        leftArrow.setOnClickListener(this);
        rightArrow.setOnClickListener(this);
        userArrayList = DBUtils.getAllUsers(this);
        ArrayList<User> allUsers = DBUtils.getAllUsers(this);
        mDatabaseUser.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                userName = dataSnapshot.child("name").getValue().toString();
                userGrade = dataSnapshot.child("gradeName").getValue().toString();
                userClass = dataSnapshot.child("className").getValue().toString();
                status = dataSnapshot.child("type").getValue().toString();
                school = dataSnapshot.child("schoolName").getValue().toString();
                currentUser = dataSnapshot.child("usersID").getValue().toString();

                Utils.sharedUerId(AttendanceActivity.this,currentUser);

                name.setText(userName);
                grade.setText(userGrade);

                for(int i = 0; i < userArrayList.size(); i++){
                    if(userArrayList.get(i).getType().equalsIgnoreCase("Student")) {
                        if(userArrayList.get(i).getSchoolName().equalsIgnoreCase(school)){
                            if(userArrayList.get(i).getGradeName().equalsIgnoreCase(userGrade)){
                                if(userArrayList.get(i).getClassName().equalsIgnoreCase(userClass)){
                                    studentNamesList.add(userArrayList.get(i).getName());
                                }
                            }
                        }
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        if(!studentNamesList.isEmpty()){
            studentName.setText(studentNamesList.get(index));

            index = index + 1;
        }

        if(index == 0){
            leftArrow.setEnabled(false);
        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logo_menu, menu);

        return true;
    }

    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {
        String advertisementId = UUID.randomUUID().toString();
        if(v.getId() == R.id.leftArrow){
            if(index == 0){
                leftArrow.setEnabled(false);
                rightArrow.setEnabled(true);
            }else {
                index = index - 1;
                studentName.setText(studentNamesList.get(index));
                rightArrow.setEnabled(true);
            }

        } if(v.getId() == R.id.rightArrow){
            if(index < studentNamesList.size()){
                leftArrow.setEnabled(true);
                studentName.setText(studentNamesList.get(index));
                index = index + 1;
            }else {
                rightArrow.setEnabled(false);
                leftArrow.setEnabled(true);
            }
        }

        if(v.getId() == R.id.buttonPending){
            @SuppressLint({"SimpleDateFormat", "NewApi", "LocalSuppress"}) SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();


            final Attends attends = new Attends();
            attends.setAttendsID(advertisementId);
            attends.setAttendsID(formatter.format(date));
            attends.setAttendsMarkerID(Utils.getSharedUerId(this));
            attends.setAttendsStatus("Pending");
            attends.setAttendsStudent(userArrayList.get(index).getUsersID());

            FirebaseDatabase.getInstance().getReference("Attends").child(advertisementId).setValue(attends).addOnCompleteListener(new OnCompleteListener<Void>() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        // boolean isSuccess = DBUtils.insert_advertisement_table(attends, AttendanceActivity.this);
                        // Log.d("ADVERTISEMENT", "isSuccess: " + isSuccess);
                        startActivity(new Intent(AttendanceActivity.this, MainActivity.class));

                    } else {
                        Toast.makeText(AttendanceActivity.this, getString(R.string.error) + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        if(v.getId() == R.id.buttonAbsent){
            @SuppressLint({"SimpleDateFormat", "NewApi", "LocalSuppress"}) SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();

            final Attends attends = new Attends();
            attends.setAttendsID(advertisementId);
            attends.setAttendsID(formatter.format(date));
            attends.setAttendsMarkerID(Utils.getSharedUerId(this));
            attends.setAttendsStatus("Pending");
            attends.setAttendsStudent(userArrayList.get(index).getUsersID());

            FirebaseDatabase.getInstance().getReference("Attends").child(advertisementId).setValue(attends).addOnCompleteListener(new OnCompleteListener<Void>() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        // boolean isSuccess = DBUtils.insert_advertisement_table(attends, AttendanceActivity.this);
                        // Log.d("ADVERTISEMENT", "isSuccess: " + isSuccess);
                        startActivity(new Intent(AttendanceActivity.this, MainActivity.class));

                    } else {
                        Toast.makeText(AttendanceActivity.this, getString(R.string.error) + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        if(v.getId() == R.id.buttonPresent){
            @SuppressLint({"SimpleDateFormat", "NewApi", "LocalSuppress"}) SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();

            final Attends attends = new Attends();
            attends.setAttendsID(advertisementId);
            attends.setAttendsID(formatter.format(date));
            attends.setAttendsMarkerID(Utils.getSharedUerId(this));
            attends.setAttendsStatus("Pending");
            attends.setAttendsStudent(userArrayList.get(index).getUsersID());

            FirebaseDatabase.getInstance().getReference("Attends").child(advertisementId).setValue(attends).addOnCompleteListener(new OnCompleteListener<Void>() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        // boolean isSuccess = DBUtils.insert_advertisement_table(attends, AttendanceActivity.this);
                        // Log.d("ADVERTISEMENT", "isSuccess: " + isSuccess);
                        startActivity(new Intent(AttendanceActivity.this, MainActivity.class));

                    } else {
                        Toast.makeText(AttendanceActivity.this, getString(R.string.error) + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }
}
