package com.example.asus.project_entertainment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.asus.project_entertainment.Fragment.Fragment_Danh_Sach_Kenh;
import com.example.asus.project_entertainment.Fragment.Fragment_TrangChu;
import com.example.asus.project_entertainment.Fragment.Fragment_Yeu_Thich;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationButton();

    }


    private void NavigationButton() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation_bottom_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_item1:
                                selectedFragment = Fragment_TrangChu.newInstance();
                                getSupportActionBar().setTitle("Trang Chủ");
                                break;
                            case R.id.action_item2:
                                selectedFragment = Fragment_Danh_Sach_Kenh.newInstance();
                                getSupportActionBar().setTitle("Danh Sách Kênh");
                                break;
                            case R.id.action_item3:
                                selectedFragment = Fragment_Yeu_Thich.newInstance();
                                getSupportActionBar().setTitle("Yêu Thích");
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout_main, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout_main, Fragment_TrangChu.newInstance());
        transaction.commit();
    }


//    private void Toolbara() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
//        setSupportActionBar(toolbar);
//// Remove default title text
//        if (getSupportActionBar() != null){
//            getSupportActionBar().setDisplayShowTitleEnabled(false);
//            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        }
//    }
}
