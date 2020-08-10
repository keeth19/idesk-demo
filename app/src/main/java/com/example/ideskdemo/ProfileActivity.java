package com.example.ideskdemo;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.ideskdemo.util.Utilize;
import com.example.ideskdemo.util.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.Drawer;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvProName,tvName, tvRegEmail, tvAccType, tvPhoneNo, tvGrade, tvSchool, tvClass, tvAttends,tvVerified,verifyMsg,verifiedMsg;
    private CircleImageView mProfileImage;
    FloatingActionButton refresh;

    private DatabaseReference mDatabaseUser;
    private FirebaseUser mUser;
    private StorageReference mStorage;
    private DatabaseReference mDatabasePost;
    private StorageReference filepath;
    private DatabaseReference user_db;
    FirebaseAuth mAuth;

    private String proName;
    private String name;
    private String email;
    private String accType;
    private String phone;
    private String loc;
    private String attends;
    private String school;
    private String grade;
    private String classS;
    private String image;
    private Uri mImageUri = null;

    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvProName = findViewById(R.id.tv_name);
        tvName = findViewById(R.id.tvName);
        tvRegEmail = findViewById(R.id.tvEmail);
        tvAccType = findViewById(R.id.tvAccType);
        tvPhoneNo = findViewById(R.id.tvPhoneNo);
        tvGrade = findViewById(R.id.tvGrade);
        tvSchool = findViewById(R.id.tvSchool);
        tvClass = findViewById(R.id.tvClass);
        tvAttends = findViewById(R.id.tvAttends);
        mProfileImage = findViewById(R.id.profileImage);

        //tvVerified = (TextView) findViewById(R.id.tv_verified);
        verifyMsg = findViewById(R.id.verifyMsg);
        verifiedMsg = findViewById(R.id.verifiedMsg);
        //refresh = (FloatingActionButton) findViewById(R.id.refreshBtn);


        /*Firebase*/
        mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("Users");
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        mStorage = FirebaseStorage.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();

        Activity mActivity = this;

        Toolbar mToolBar = findViewById(R.id.login_toolbar);
        setSupportActionBar(mToolBar);

        AccountHeader headerResult  = Utilize.profileHeader(savedInstanceState, mActivity);
        Drawer result               = Utilize.drawerBody(mToolBar, savedInstanceState, mActivity);

        //ratingBar
        final RatingBar ratingBar = findViewById(R.id.rating);
        ratingBar.setRating(5);

        ImageView edit_profile = findViewById(R.id.edit_profile);
        edit_profile.setOnClickListener(this);


        if (Utils.getSingIn(this)) {

            userId = mAuth.getCurrentUser().getUid();
            mUser = mAuth.getCurrentUser();

            if (!mUser.isEmailVerified()) {
                // verifyMsg.setVisibility(View.VISIBLE);
                if (mUser != null) {
                    verifiedMsg.setText(getString(R.string.emailpassword_status_fmt,
                            mUser.getEmail(), mUser.isEmailVerified()));
                }
            }


            mDatabaseUser.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    proName = dataSnapshot.child("name").getValue().toString();
                    name = dataSnapshot.child("name").getValue().toString();
                    email = dataSnapshot.child("email").getValue().toString();
                    phone = dataSnapshot.child("phone").getValue().toString();
                    school = dataSnapshot.child("schoolName").getValue().toString();
                    classS = dataSnapshot.child("className").getValue().toString();
                    grade = dataSnapshot.child("gradeName").getValue().toString();
                    accType = dataSnapshot.child("type").getValue().toString();
                    attends = dataSnapshot.child("attendantStatus").getValue().toString();

                    if (((dataSnapshot.child("image").getValue().toString()).isEmpty())) {
                    } else {
                        image = dataSnapshot.child("image").getValue().toString();
                        Picasso.get().load(image).into(mProfileImage);
                    }

                    tvAccType.setText(accType);
                    tvProName.setText(proName);
                    tvName.setText(name);
                    tvRegEmail.setText(email);
                    tvPhoneNo.setText(phone);
                    tvSchool.setText(school);

                    TextView tvClassLabel = findViewById(R.id.tvClassLabel);
                    TextView tvGradeLabel = findViewById(R.id.tvGradeLabel);
                    if(!accType.equalsIgnoreCase("Principal")){
                        tvGrade.setText(grade);
                        tvClass.setText(classS);
                        tvClassLabel.setVisibility(View.VISIBLE);
                        tvGradeLabel.setVisibility(View.VISIBLE);
                    }else {
                        tvClassLabel.setVisibility(View.GONE);
                        tvGradeLabel.setVisibility(View.GONE);
                    }

                    TextView tvAttendsLabel = findViewById(R.id.tvAttendsLabel);
                    if(accType.equalsIgnoreCase("Student")){
                        tvAttends.setText(attends);
                        tvAttends.setVisibility(View.VISIBLE);
                        tvAttendsLabel.setVisibility(View.VISIBLE);
                    }else {
                        tvAttends.setVisibility(View.GONE);
                        tvAttendsLabel.setVisibility(View.GONE);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }else {
            startActivity(new Intent(ProfileActivity.this,StartActivity.class));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.refresh_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId_ = item.getItemId();
        if (itemId_ == R.id.action_refresh) {
            mUser.reload().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (mUser != null) {
                        verifiedMsg.setText(getString(R.string.emailpassword_status_fmt,
                                mUser.getEmail(), mUser.isEmailVerified()));
                    }
                    // verifyMsg.setText("Email Verified");
                }
            });
            return true;
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.edit_profile){
          //  startActivity(new Intent(ProfileActivity.this,EditProfileActivity.class));
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ProfileActivity.this,MainActivity.class));
    }
}