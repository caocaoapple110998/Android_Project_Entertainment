package com.example.asus.project_entertainment.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.asus.project_entertainment.Activity.PlayVideo_Activity_TrangChu;
import com.example.asus.project_entertainment.Adapter.Video_TrangChu_Adapter;
import com.example.asus.project_entertainment.Control.Video_TrangChu;
import com.example.asus.project_entertainment.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Fragment_TrangChu extends Fragment {

    public static String API_KEY = "AIzaSyCCCHSLyMaslkvBTuPqsM7e7DNun5AIPb8";
    String ID_PLAYLIST_ANTIA_TT = "PLEyKu1JwbU4tM8Pjw8ggfEnd9o7xJuGZm";
    //String ID_PLAYLIST_ANTIA_TT = "PLCJJ2bTVr6ucLfj8JSKcCNboIHjtiSh2F";

    String urlGetJson = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId="+ ID_PLAYLIST_ANTIA_TT +"&key="+ API_KEY +"&maxResults=50";

    ListView lv_Video_TrangChu;
    ArrayList<Video_TrangChu> arrayTrangChu;
    Video_TrangChu_Adapter adapter;

    public static Fragment_TrangChu newInstance() {
        Fragment_TrangChu fragment = new Fragment_TrangChu();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trangchu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        onClickPlayVideo(view);

        initEvent(view);
        GetJsonYoutobe(urlGetJson);
    }

    private void onClickPlayVideo(View view) {
        lv_Video_TrangChu = (ListView)view.findViewById(R.id.lv_trangchu);
        lv_Video_TrangChu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), PlayVideo_Activity_TrangChu.class);
                intent.putExtra("idVideoYoutobe", arrayTrangChu.get(i).getIdVideo());
                startActivity(intent);
            }
        });
    }

    private void initEvent(View view) {
        lv_Video_TrangChu = (ListView)view.findViewById(R.id.lv_trangchu);
        arrayTrangChu = new ArrayList<>();
        adapter = new Video_TrangChu_Adapter(getActivity(), R.layout.dong_hien_thi_video, arrayTrangChu);
        lv_Video_TrangChu.setAdapter(adapter);
    }

    private void GetJsonYoutobe(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonItems = response.getJSONArray("items");

                            String title ="";
                            String url ="";
                            String idVideo ="";

                            for (int i =0; i < jsonItems.length(); i++){
                                JSONObject jsonItem = jsonItems.getJSONObject(i);
                                JSONObject jsonSnippet = jsonItem.getJSONObject("snippet");
                                title = jsonSnippet.getString("title");
                                JSONObject jsonThumbnail = jsonSnippet.getJSONObject("thumbnails");
                                JSONObject jsonMedium = jsonThumbnail.getJSONObject("medium");
                                url = jsonMedium.getString("url");
                                JSONObject jsonResourceID = jsonSnippet.getJSONObject("resourceId");
                                idVideo = jsonResourceID.getString("videoId");
                                arrayTrangChu.add(new Video_TrangChu(title, url, idVideo));
//                                Toast.makeText(MainActivity.this, idVideo, Toast.LENGTH_LONG).show();
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

//                        Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Loi", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
