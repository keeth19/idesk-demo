package com.example.ideskdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.ideskdemo.util.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout mLoginEmail;
    private TextInputLayout mLoginPassword;

    private String parentDbName = "User";

    private EditText etEmail, etPassword, editTextDeviceModel, editTextOS, loginTrue;

    private ProgressDialog mLoginProgress;

    FirebaseAuth fireAuth, mAuth;
    Context mcontext;
    //  private DatabaseReference mUserDatabase;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mcontext = LoginActivity.this;

        fireAuth = FirebaseAuth.getInstance();
        mAuth = FirebaseAuth.getInstance();

        Toolbar mToolbar = findViewById(R.id.login_toolbar);
        setSupportActionBar(mToolbar);
        // Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        mLoginProgress = new ProgressDialog(this);

        //    mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users");


        mLoginEmail = findViewById(R.id.login_email);
        mLoginPassword = findViewById(R.id.login_password);
        final Button mLogin_btn = findViewById(R.id.login_btn);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        Button btnLogin = findViewById(R.id.login_btn);

        //device reading
        editTextDeviceModel = findViewById(R.id.et_device_model);
        editTextOS = findViewById(R.id.et_device_os);
        editTextDeviceModel.setVisibility(View.GONE);
        editTextOS.setVisibility(View.GONE);

        loginTrue = findViewById(R.id.et_loginTrue);
        loginTrue.setVisibility(View.GONE);

        /*mLogin_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                String email = Objects.requireNonNull(mLoginEmail.getEditText()).getText().toString();
                String password = Objects.requireNonNull(mLoginPassword.getEditText()).getText().toString();

                if(!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)){

                    mLoginProgress.setTitle("Logging In");
                    mLoginProgress.setMessage("Please wait while we check your credentials.");
                    mLoginProgress.setCanceledOnTouchOutside(false);
                    mLoginProgress.show();
                    Intent mainIntent = new Intent(LoginActivity.this, CreateAdActivity.class);
                    startActivity(mainIntent);
                //    loginUser(email, password);

                }
                Intent mainIntent = new Intent(LoginActivity.this, CreateAdActivity.class);
                startActivity(mainIntent);
            }
        });*/

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = etEmail.getText().toString().trim();
                final String password = etPassword.getText().toString().trim();

                final String deviceModel1 = Build.MANUFACTURER + " " + Build.MODEL;
                final String os1 = "Android";

                if (TextUtils.isEmpty(email)) {
                    etEmail.setError(getString(R.string.input_error_email));
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    etEmail.setError(getString(R.string.input_error_email_invalid));
                    etEmail.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    etPassword.setError(getString(R.string.input_error_password));
                    return;
                }
                if (password.length() < 6) {
                    etPassword.setError(getString(R.string.input_error_password_length));
                    return;
                }

                mLoginProgress.setTitle(getString(R.string.progressbar_loginTitle));
                mLoginProgress.setMessage(getString(R.string.progressbar_message));
                mLoginProgress.setCanceledOnTouchOutside(false);
                mLoginProgress.show();

                fireAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(LoginActivity.this, getString(R.string.login_success), Toast.LENGTH_SHORT).show();
                            mLoginProgress.dismiss();
                            Utils.updateSingIn(true, getApplicationContext());

                            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            Log.e("CLIENT ID", "onCreate: " + userId);
                            Utils.sharedUerId(mcontext, userId);

                            Log.e("CLIENT ID", "onCreate: " + userId);
                            Utils.sharedUerId(mcontext, userId);
                            DatabaseReference mUserRef = null;
                            FirebaseAuth mAuth;
                            mAuth = FirebaseAuth.getInstance();
                            if (mAuth.getCurrentUser() != null) {
                                mUserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid());
                                try {
                                    mUserRef.child("online").setValue("true");
                                } catch (Exception e) {

                                }
                            }
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        } else {
                            Toast.makeText(LoginActivity.this, getString(R.string.error) + getString(R.string.error_login) + "   " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            mLoginProgress.dismiss();
                        }
                    }
                });

            }
        });

       /* mRegister_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));

            }
        });*/
    }
}