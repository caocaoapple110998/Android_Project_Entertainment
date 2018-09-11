package com.example.asus.project_entertainment.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.asus.project_entertainment.R;

public class HaiKich_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hai_kich);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_haikich_activity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Hài Kịch");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
