package com.example.ideskdemo.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;

import com.example.ideskdemo.AttendanceActivity;
import com.example.ideskdemo.MainActivity;
import com.example.ideskdemo.ProfileActivity;
import com.example.ideskdemo.R;
import com.example.ideskdemo.SplashActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class Utilize {
    private static AccountHeader headerResult = null;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static AccountHeader profileHeader(Bundle savedInstanceState, final Activity mActivity) {
        // Create the AccountHeader

        ProfileDrawerItem profileDrawerItem = new ProfileDrawerItem()
                .withName("Name")
                .withEmail("mail@gmail,com")
                .withIcon(mActivity.getResources().getDrawable(R.drawable.profile_filled_299075));

        headerResult = new AccountHeaderBuilder()
                .withActivity(mActivity)
                .withTranslucentStatusBar(true)
                .withCompactStyle(false)
                .withHeaderBackground(R.color.colorPrimary)
                .withCurrentProfileHiddenInList(true)
                .withSavedInstance(savedInstanceState)
                .build();

        return headerResult;
    }

    public static Drawer drawerBody(Toolbar toolbar, Bundle savedInstanceState, final Activity mActivity) {
        final Drawer newDrawer;
        newDrawer = new DrawerBuilder()
                .withActivity(mActivity)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult) // set the AccountHeader we created earlier for the header
                .addDrawerItems(
                ) // add the items we want to use with our Drawer
                .addStickyDrawerItems(

                )
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {

                    }
                    @Override
                    public void onDrawerClosed(View drawerView) {

                    }
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {

                    }
                })
                .withActionBarDrawerToggleAnimated(true)
                .withSavedInstance(savedInstanceState)
                .withDisplayBelowStatusBar(true)
                .build();

        PrimaryDrawerItem home;

        home = new PrimaryDrawerItem()
                .withName(R.string.home)
                .withIcon(mActivity.getResources().getDrawable(R.drawable.outline_home_black_36))
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        mActivity.startActivity(new Intent(mActivity, MainActivity.class));
                        return false;
                    }
                })
                .withIdentifier(1);
        newDrawer.addItem(home);

        PrimaryDrawerItem Profile;
        Profile = new PrimaryDrawerItem()
                .withName(R.string.profile)
                .withIcon(mActivity.getResources().getDrawable(R.drawable.check))
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        mActivity.startActivity(new Intent(mActivity, ProfileActivity.class));
                        return false;
                    }
                })
                .withIdentifier(2);
        newDrawer.addItem(Profile);

        PrimaryDrawerItem AdvancedSearch;

        PrimaryDrawerItem attendance;
        attendance = new PrimaryDrawerItem()
                .withName("Attendance")
                .withIcon(mActivity.getResources().getDrawable(R.drawable.check))
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        mActivity.startActivity(new Intent(mActivity, AttendanceActivity.class));
                        return false;
                    }
                })
                .withIdentifier(2);
        newDrawer.addItem(attendance);



        PrimaryDrawerItem logout ;

        logout = new PrimaryDrawerItem()
                .withName(R.string.logout)
                .withIcon(mActivity.getResources().getDrawable(R.drawable.exit2_1564506))
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        DatabaseReference mUserRef = null;
                        FirebaseAuth mAuth;
                        mAuth = FirebaseAuth.getInstance();
                        if (mAuth.getCurrentUser() != null) {
                            mUserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid());
                            try {
                                mUserRef.child("online").setValue("true");
                            }catch (Exception e){

                            }
                            Utils.updateSingIn(false,mActivity);
                            FirebaseAuth.getInstance().signOut();
                            sendToStart(mActivity);
                        }

                        return false;
                    }
                })
                .withIdentifier(4);
        newDrawer.addItem(logout);

        return newDrawer;

    }

    private static void sendToStart(Activity activity) {

        Intent startIntent = new Intent(activity, SplashActivity.class);
        activity.startActivity(startIntent);
        activity.finish();

    }
}
