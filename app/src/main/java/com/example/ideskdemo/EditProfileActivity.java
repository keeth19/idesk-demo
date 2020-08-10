package com.example.ideskdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends AppCompatActivity {
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;

    private CircleImageView mDisplayImage;
    EditText etProName,etProEmail,etProPhone,etProIntro,editTextDeviceModel, editTextOS;
    Spinner spProType,spProLocation;
    Button btnUpdate,btnCancel;
    FloatingActionButton btnChoose;

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;

    FirebaseStorage storage;
    StorageReference storageReference;
    private DatabaseReference mDatabaseUser;
    private FirebaseUser mUser;

    String download_url;
    String thumb_downloadUrl;
    private String name;
    private String email;
    private String phone;
    private String image;
    private String info;

    //ProgressDialog
    private ProgressDialog mRegProgress;

    private static final int GALLERY_PICK = 1;
    private String imagePath ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
    }
}