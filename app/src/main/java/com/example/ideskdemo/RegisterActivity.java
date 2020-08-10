package com.example.ideskdemo;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;

import com.example.countrycode.CountryCodeActivity;
import com.example.countrycode.countrycode.Country;
import com.example.countrycode.countrycode.CountryUtils;
import com.example.countrycode.utility.Utility;
import com.example.ideskdemo.database.DBUtils;
import com.example.ideskdemo.object.User;
import com.example.ideskdemo.util.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private TextInputLayout mDisplayName;
    private TextInputLayout mEmail;
    private TextInputLayout mPhone;
    private TextInputLayout mPassword;

    private AppCompatEditText etCountryCode;

    private EditText etRegName, etRegEmail, etRegPassword, etPhoneNo, editTextDeviceModel, editTextOS;
    private FirebaseAuth mAuth;
    ArrayList<User> usersArrayList;

    //  private DatabaseReference mDatabase;

    //ProgressDialog
    private ProgressDialog mRegProgress;

    private static final int COUNTRYCODE_ACTION = 1;
    private Spinner userTypeSpn;
    private Spinner schoolSpn;
    private Spinner gradeSpn;
    private Spinner classSpn;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etRegName = findViewById(R.id.et_name);
        etRegEmail = findViewById(R.id.et_reg_email);
        etRegPassword = findViewById(R.id.et_reg_password);
        etPhoneNo = findViewById(R.id.et_reg_phone);

        //device reading
        editTextDeviceModel = findViewById(R.id.et_device_model);
        editTextOS = findViewById(R.id.et_device_os);
        editTextDeviceModel.setVisibility(View.GONE);
        editTextOS.setVisibility(View.GONE);

        //Toolbar Set
        Toolbar mToolbar = findViewById(R.id.register_toolbar);
        setSupportActionBar(mToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Create Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRegProgress = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();

        usersArrayList = DBUtils.getAllUsers(this);


        mDisplayName = findViewById(R.id.register_display_name);
        mEmail = findViewById(R.id.register_email);
        mPhone = findViewById(R.id.reg_phone);
        mPassword = findViewById(R.id.reg_password);
        Button mCreateBtn = findViewById(R.id.reg_create_btn);
        ImageView imgFlag = findViewById(R.id.flag_imv);
        etCountryCode = findViewById(R.id.etCountryCode);

        userTypeSpn = findViewById(R.id.selectType);
        schoolSpn = findViewById(R.id.selectSchool);
        gradeSpn = findViewById(R.id.selectGrade);
        classSpn = findViewById(R.id.selectClass);

        gradeSpn.setVisibility(View.GONE);
        classSpn.setVisibility(View.GONE);

        final String[] userType = getResources().getStringArray(R.array.user_type);

        ArrayAdapter<String> userTypeAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item_custom, userType);
        userTypeSpn.setAdapter(userTypeAdapter);
        userTypeSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(userType[position].equalsIgnoreCase("select user type") || userType[position].equalsIgnoreCase("Principal")){
                    gradeSpn.setVisibility(View.GONE);
                    classSpn.setVisibility(View.GONE);
                }else {
                    gradeSpn.setVisibility(View.VISIBLE);
                    classSpn.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String[] schoolList = getResources().getStringArray(R.array.school);

        ArrayAdapter<String> schoolAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item_custom, schoolList);
        schoolSpn.setAdapter(schoolAdapter);
        schoolSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String[] gradeList = getResources().getStringArray(R.array.grade);

        ArrayAdapter<String> gradeAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item_custom, gradeList);
        gradeSpn.setAdapter(gradeAdapter);
        gradeSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String[] classList = getResources().getStringArray(R.array.classs);

        ArrayAdapter<String> classAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item_custom, classList);
        classSpn.setAdapter(classAdapter);
        classSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                @SuppressLint({"NewApi", "LocalSuppress"})String display_name = Objects.requireNonNull(mDisplayName.getEditText()).getText().toString();
                @SuppressLint({"NewApi", "LocalSuppress"})String email = Objects.requireNonNull(mEmail.getEditText()).getText().toString();
                @SuppressLint({"NewApi", "LocalSuppress"})String password = Objects.requireNonNull(mPassword.getEditText()).getText().toString();
                @SuppressLint({"NewApi", "LocalSuppress"})String phone = Objects.requireNonNull(etCountryCode.getText()).toString() + Objects.requireNonNull(mPhone.getEditText()).getText().toString();

                if(!TextUtils.isEmpty(display_name) || !TextUtils.isEmpty(email) || !TextUtils.isEmpty(password) || !TextUtils.isEmpty(phone)){

                    mRegProgress.setTitle("Registering User");
                    mRegProgress.setMessage("Please wait while we create your account !");
                    mRegProgress.setCanceledOnTouchOutside(false);
                    mRegProgress.show();

                    //      register_user(display_name, email, password,phone);

                    Intent mainIntent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(mainIntent);

                    mRegProgress.dismiss();

                }

                Intent mainIntent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(mainIntent);

            }
        });

        //noinspection AccessStaticViaInstance
        TelephonyManager tm = (TelephonyManager) getSystemService(getApplicationContext().TELEPHONY_SERVICE);
        assert tm != null;
        String countryISO = tm.getNetworkCountryIso();
        String countryNumber = "";
        String countryName = "";
        Utility.log(countryISO);

        Country mSelectedCountry;
        if(!TextUtils.isEmpty(countryISO))
        {
            for (Country country : CountryUtils.getAllCountries(this)) {
                if (countryISO.toLowerCase().equalsIgnoreCase(country.getIso().toLowerCase())) {
                    countryNumber = country.getPhoneCode();
                    countryName = country.getName();
                    break;
                }
            }
            Country country = new Country(countryISO,
                    countryNumber,
                    countryName);
            mSelectedCountry = country;
            etCountryCode.setText("+" + country.getPhoneCode() + "");
            imgFlag.setImageResource(CountryUtils.getFlagDrawableResId(country.getIso()));
            Utility.log(countryNumber);
        }
        else {
            Country country = new Country(getString(R.string.country_united_states_code),
                    getString(R.string.country_united_states_number),
                    getString(R.string.country_united_states_name));
            mSelectedCountry = country;
            etCountryCode.setText("+" + country.getPhoneCode() + "");
            imgFlag.setImageResource(CountryUtils.getFlagDrawableResId(country.getIso()));
            Utility.log(countryNumber);
        }

        etCountryCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.hideKeyBoardFromView(RegisterActivity.this);
                mPhone.setError(null);
                Intent intent = new Intent(RegisterActivity.this, CountryCodeActivity.class);
                intent.putExtra("TITLE", getResources().getString(R.string.app_name));
                startActivityForResult(intent, COUNTRYCODE_ACTION);
            }
        });

        findViewById(R.id.reg_create_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reg_create_btn:
                if(!schoolSpn.getSelectedItem().toString().equalsIgnoreCase("select user type") && !schoolSpn.getSelectedItem().toString().equalsIgnoreCase("Principal")){

                    if(!userTypeSpn.getSelectedItem().toString().equalsIgnoreCase("select user type") && !schoolSpn.getSelectedItem().toString().equalsIgnoreCase("select school")
                            && !gradeSpn.getSelectedItem().toString().equalsIgnoreCase("select grade") && !classSpn.getSelectedItem().toString().equalsIgnoreCase("select class")){

                        registerUser();

                    }else {

                    }
                }else {

                    if(!userTypeSpn.getSelectedItem().toString().equalsIgnoreCase("select user type") && !schoolSpn.getSelectedItem().toString().equalsIgnoreCase("select school")){

                        registerUser();

                    }else {

                    }
                }

                break;
        }
    }

    private void registerUser() {

        int charIndex = 0;

        final String name = etRegName.getText().toString().trim();
        final String email = etRegEmail.getText().toString().trim();
        final String phone = etPhoneNo.getText().toString().trim();
        final String phoneNumber = phone.substring(0, charIndex) + phone.substring(charIndex+1);
        final String uerType = userTypeSpn.getSelectedItem().toString();
        final String school = schoolSpn.getSelectedItem().toString();
        final String grade = gradeSpn.getSelectedItem().toString();
        final String className = classSpn.getSelectedItem().toString();



        final String password = etRegPassword.getText().toString().trim();

        final String deviceModel = Build.MANUFACTURER + " " + Build.MODEL;
        final String os = "Android";

        if (name.isEmpty()) {
            etRegName.setError(getString(R.string.input_error_name));
            etRegName.requestFocus();
            return;
        }if (email.isEmpty()) {
            etRegEmail.setError(getString(R.string.input_error_email));
            etRegEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etRegEmail.setError(getString(R.string.input_error_email_invalid));
            etRegEmail.requestFocus();
            return;
        }
        if (phone.isEmpty()) {
            etPhoneNo.setError(getString(R.string.input_error_phone));
            etPhoneNo.requestFocus();
            return;
        }
        if (phone.length() != 10) {
            etPhoneNo.setError(getString(R.string.input_error_phone_invalid));
            etPhoneNo.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            etRegPassword.setError(getString(R.string.input_error_password));
            etRegPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            etRegPassword.setError(getString(R.string.input_error_password_length));
            etRegPassword.requestFocus();
            return;
        }


        if(!name.isEmpty() && !email.isEmpty() && !phone.isEmpty() && !password.isEmpty()) {

            mRegProgress.setTitle(getString(R.string.progressbar_registerTitle));
            mRegProgress.setMessage(getString(R.string.progressbar_message));
            mRegProgress.setCanceledOnTouchOutside(false);
            mRegProgress.show();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                // send verification link
                                final FirebaseUser fuser = mAuth.getCurrentUser();
                                fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(RegisterActivity.this, getString(R.string.verification_email_has_been_sent_), Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(RegisterActivity.this, getString(R.string.verification_email_not_sent), Toast.LENGTH_SHORT).show();
                                        Log.d("TAG", "onFailure: Email not sent " + e.getMessage());
                                    }
                                });

                                final User user = new User();
                                String userId = UUID.randomUUID().toString();
                                user.setUsersID(userId);
                                user.setName(name);
                                user.setEmail(email);
                                user.setPassword(password);
                                user.setPhone(phoneNumber);
                                user.setDeviceModel(deviceModel);
                                user.setOs(os);
                                user.setType(uerType);
                                user.setImage("");
                                user.setSchoolName(school);
                                user.setGradeName(grade);
                                user.setClassName(className);
                                user.setAttendantStatus("");

                           /* User user1 = new User(
                                    name,
                                    email,
                                    phone,
                                    password,
                                    deviceModel,
                                    os
                            );
*/
                                FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {

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

                                            Utils.updateSingIn(true, getApplicationContext());
                                            Toast.makeText(RegisterActivity.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                            mRegProgress.dismiss();
                                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));

                                        } else {
                                            mRegProgress.dismiss();
                                            Toast.makeText(RegisterActivity.this, getString(R.string.error) + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            } else {
                                Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                mRegProgress.dismiss();
                            }
                        }
                    });
        }else {
            if (name.isEmpty()) {
                etRegName.setError(getString(R.string.input_error_name));
                etRegName.requestFocus();

            }if (email.isEmpty()) {
                etRegEmail.setError(getString(R.string.input_error_email));
                etRegEmail.requestFocus();

            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etRegEmail.setError(getString(R.string.input_error_email_invalid));
                etRegEmail.requestFocus();

            }
            if (phone.isEmpty()) {
                etPhoneNo.setError(getString(R.string.input_error_phone));
                etPhoneNo.requestFocus();

            }
            if (phone.length() != 10) {
                etPhoneNo.setError(getString(R.string.input_error_phone_invalid));
                etPhoneNo.requestFocus();

            }
            if (password.isEmpty()) {
                etRegPassword.setError(getString(R.string.input_error_password));
                etRegPassword.requestFocus();

            }

            if (password.length() < 6) {
                etRegPassword.setError(getString(R.string.input_error_password_length));
                etRegPassword.requestFocus();
            }
        }
    }
}