package com.example.countrycode;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countrycode.countrycode.Country;
import com.example.countrycode.countrycode.CountryCodeAdapter;
import com.example.countrycode.countrycode.CountryUtils;
import com.example.countrycode.recycler.FastScrollRecyclerViewItemDecoration;
import com.example.countrycode.utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

public class CountryCodeActivity extends AppCompatActivity {
    public  String title = "";

    private AppCompatEditText etSearchCountry;
    private AppCompatTextView tvNoResult;
    private RecyclerView rvCountryCode;
    private final Activity mActivity = CountryCodeActivity.this;
    private final List<Country> masterCountries = new ArrayList<>();
    private List<Country> mFilteredCountries;
    private List<Country> mTempCountries;
    private CountryCodeAdapter mAdapter;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_code);
        Utility.hideSoftKeyboard(mActivity);
        setupUI();
        setUpToolBar();
        setUpDtata();
    }

    private void setupUI() {
        RelativeLayout rlCountry = findViewById(R.id.rlCountry);
        rvCountryCode = findViewById(R.id.rvCountryCode);
        etSearchCountry = findViewById(R.id.etSearchCountry);
        tvNoResult = findViewById(R.id.tvNoResult);
        rvCountryCode.setHasFixedSize(true);
        AppCompatTextView tvToolbarTitle = findViewById(R.id.tvToolbarTitle);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setUpToolBar() {
        Toolbar mToolBar = findViewById(R.id.toolbar);
        mToolBar.setTitleTextColor(ContextCompat.getColor(mActivity, R.color.white));
        setSupportActionBar(mToolBar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if(getIntent().hasExtra("TITLE")&&getIntent().getStringExtra("TITLE")!=null&& !Objects.requireNonNull(getIntent().getStringExtra("TITLE")).equalsIgnoreCase(""))
        {
            title = getIntent().getStringExtra("TITLE");
            getSupportActionBar().setTitle(title);

        }
        else

        {
            getSupportActionBar().setTitle("Select Country");

        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setUpDtata() {

        etSearchCountry.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                applyQuery(s.toString());
            }
        });

        CountryCodeAdapter.Callback callback = new CountryCodeAdapter.Callback() {
            @Override
            public void onItemCountrySelected(Country country) {
                Log.e("Phone Code ", "" + country.getPhoneCode());
                Utility.hideKeyBoardFromView(mActivity);
                Intent intent = new Intent();
                intent.putExtra(AppConstant.COUNTRY, country);
                setResult(RESULT_OK, intent);
                finish();
            }
        };
        masterCountries.addAll(CountryUtils.getAllCountries(mActivity));
        this.mFilteredCountries = getFilteredCountries();
        HashMap<String, Integer> mapIndex = calculateIndexesForName(mFilteredCountries);
        mAdapter = new CountryCodeAdapter(mFilteredCountries, callback, mapIndex);


        rvCountryCode.setLayoutManager(new LinearLayoutManager(mActivity));
        rvCountryCode.setAdapter(mAdapter);
        FastScrollRecyclerViewItemDecoration decoration = new FastScrollRecyclerViewItemDecoration(this);
        rvCountryCode.addItemDecoration(decoration);
        rvCountryCode.setItemAnimator(new DefaultItemAnimator());
    }

    private HashMap<String, Integer> calculateIndexesForName(List<Country> items) {
        HashMap<String, Integer> mapIndex = new LinkedHashMap<>();
        for (int i = 0; i < items.size(); i++) {
            String name = items.get(i).getName();
            String index = name.substring(0, 1);
            index = index.toUpperCase();

            if (!mapIndex.containsKey(index)) {
                mapIndex.put(index, i);
            }
        }
        return mapIndex;
    }

    private List<Country> getFilteredCountries() {
        return getFilteredCountries("");
    }

    private void applyQuery(String query) {
        tvNoResult.setVisibility(View.GONE);
        query = query.toLowerCase();

        //if query started from "+" ignore it
        if (query.length() > 0 && query.charAt(0) == '+') {
            query = query.substring(1);
        }

        mFilteredCountries = getFilteredCountries(query);

        if (mFilteredCountries.size() == 0) {
            tvNoResult.setVisibility(View.VISIBLE);
        }

        mAdapter.notifyDataSetChanged();
    }

    private List<Country> getFilteredCountries(String query) {
        if (mTempCountries == null) {
            mTempCountries = new ArrayList<>();
        } else {
            mTempCountries.clear();
        }


        for (Country country : masterCountries) {
            if (country.isEligibleForQuery(query)) {
                mTempCountries.add(country);
            }
        }
        return mTempCountries;
    }

}
