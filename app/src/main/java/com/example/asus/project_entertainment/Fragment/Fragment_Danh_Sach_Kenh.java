package com.example.asus.project_entertainment.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.project_entertainment.Activity.CaNhac_Activity;
import com.example.asus.project_entertainment.Activity.HaiKich_Activity;
import com.example.asus.project_entertainment.Activity.Phim_Activity;
import com.example.asus.project_entertainment.Activity.TheThao_Activity;
import com.example.asus.project_entertainment.R;

public class Fragment_Danh_Sach_Kenh extends Fragment {

    TextView txtHaiKich, txtCaNhac, txtTheThao, txtPhim;

    public static Fragment_Danh_Sach_Kenh newInstance() {
        Fragment_Danh_Sach_Kenh fragment = new Fragment_Danh_Sach_Kenh();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_danh_sach_kenh, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initControl(view);
        Onclick();
    }

    private void Onclick() {
        txtHaiKich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HaiKich_Activity.class);
                startActivity(intent);
            }
        });

        txtCaNhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CaNhac_Activity.class);
                startActivity(intent);
            }
        });

        txtTheThao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TheThao_Activity.class);
                startActivity(intent);
            }
        });

        txtPhim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Phim_Activity.class);
                startActivity(intent);
            }
        });
    }

    private void initControl(View view) {
        txtHaiKich = (TextView)view.findViewById(R.id.txt_HaiKich_DSK);
        txtCaNhac = (TextView)view.findViewById(R.id.txt_CaNhac_DSK);
        txtTheThao = (TextView)view.findViewById(R.id.txt_TheThao_DSK);
        txtPhim = (TextView)view.findViewById(R.id.txt_Phim_DSK);
    }
}
