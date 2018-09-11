package com.example.asus.project_entertainment.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.asus.project_entertainment.R;

import java.util.ArrayList;

public class TheThao_Activity extends AppCompatActivity {

    Spinner spinnerTheThao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_thao);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_thethao_activity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Thể Thao");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        initSpinner();
    }

    private void initSpinner() {
        spinnerTheThao = (Spinner)findViewById(R.id.spinner_thethao);

        final ArrayList<String> arrayTheoThao = new ArrayList<>();
        arrayTheoThao.add("Nổi Bật");
        arrayTheoThao.add("Việt Nam");
        arrayTheoThao.add("Champions League");
        arrayTheoThao.add("Ngoại Hạng Anh");
        arrayTheoThao.add("Bundesliga ( VĐQG Đức )");
        arrayTheoThao.add("Serie A ( VĐQG Ý )");

        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayTheoThao);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerTheThao.setAdapter(arrayAdapter);

        spinnerTheThao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(TheThao_Activity.this, arrayTheoThao.get(i), Toast.LENGTH_SHORT).show();
            }

        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
