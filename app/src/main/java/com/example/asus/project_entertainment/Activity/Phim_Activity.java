package com.example.asus.project_entertainment.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.asus.project_entertainment.Adapter.ListViewAdapter;
import com.example.asus.project_entertainment.Adapter.Video_Phim_Adapter;
import com.example.asus.project_entertainment.Control.AnimalNames;
import com.example.asus.project_entertainment.Control.Video_Phim;
import com.example.asus.project_entertainment.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Phim_Activity extends AppCompatActivity  implements SearchView.OnQueryTextListener{

    public static String API_KEY = "AIzaSyCCCHSLyMaslkvBTuPqsM7e7DNun5AIPb8";
    String ID_PLAYLIST_ANTIA_TT = "PLCJJ2bTVr6ucLfj8JSKcCNboIHjtiSh2F";

    String urlGetJson = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId="+ ID_PLAYLIST_ANTIA_TT +"&key="+ API_KEY +"&maxResults=50";

    TextView txt_NoiBat_Phim, txt_PhimBo_Phim, txtPhimLe_Phim;

    ListView lv_noibat_phim, lv_phimbo_phim, lv_phimle_phim;
    ArrayList<Video_Phim> arrayVideo;
    Video_Phim_Adapter adapterPhim;

    ListViewAdapter adapter;
    String[] animalNameList;
    ArrayList<AnimalNames> arraylist = new ArrayList<AnimalNames>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phim);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_phim_activity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Phim");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);


        // Search
        Search();
        onClick();
        innit();
        GetJsonYoutube(urlGetJson);
    }

    private void innit() {
        arrayVideo = new ArrayList<>();
        adapterPhim = new Video_Phim_Adapter(this, R.layout.dong_hien_thi_video, arrayVideo);
        lv_noibat_phim.setAdapter(adapterPhim);
    }

    private void onClick() {
        txt_NoiBat_Phim = (TextView)findViewById(R.id.txt_noibat_Phim_Activity);
        txt_PhimBo_Phim = (TextView)findViewById(R.id.txt_phimbo_Phim_Activity);
        txtPhimLe_Phim = (TextView)findViewById(R.id.txt_phimle_Phim_Activity);



        lv_noibat_phim = (ListView) findViewById(R.id.lv_phim_noibat);
        lv_phimbo_phim = (ListView) findViewById(R.id.lv_phim_phimbo);
        lv_phimle_phim = (ListView) findViewById(R.id.lv_phim_phimle);


        txt_NoiBat_Phim.setBackgroundResource(R.color.colorPrimary);
        lv_noibat_phim.setVisibility(View.VISIBLE);
        txt_NoiBat_Phim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_NoiBat_Phim.setBackgroundResource(R.color.colorPrimary);
                txt_PhimBo_Phim.setBackgroundResource(R.color.color_bac);
                txtPhimLe_Phim.setBackgroundResource(R.color.color_bac);

                lv_noibat_phim.setVisibility(View.VISIBLE);
                lv_phimbo_phim.setVisibility(View.GONE);
                lv_phimle_phim.setVisibility(View.GONE);
            }
        });

        txt_PhimBo_Phim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_NoiBat_Phim.setBackgroundResource(R.color.color_bac);
                txt_PhimBo_Phim.setBackgroundResource(R.color.colorPrimary);
                txtPhimLe_Phim.setBackgroundResource(R.color.color_bac);

                lv_noibat_phim.setVisibility(View.GONE);
                lv_phimbo_phim.setVisibility(View.VISIBLE);
                lv_phimle_phim.setVisibility(View.GONE);
            }
        });

        txtPhimLe_Phim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_NoiBat_Phim.setBackgroundResource(R.color.color_bac);
                txt_PhimBo_Phim.setBackgroundResource(R.color.color_bac);
                txtPhimLe_Phim.setBackgroundResource(R.color.colorPrimary);

                lv_noibat_phim.setVisibility(View.GONE);
                lv_phimbo_phim.setVisibility(View.GONE);
                lv_phimle_phim.setVisibility(View.VISIBLE);
            }
        });

        lv_noibat_phim.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(Phim_Activity.this, PlayVideo_Activity_TrangChu.class);
                intent.putExtra("idVideoYoutobe", arrayVideo.get(i).getIdVideo());
                startActivity(intent);
            }
        });
    }

    private void Search() {
        animalNameList = new String[]{"Lion", "Tiger", "Dog",
                "Cat", "Tortoise", "Rat", "Elephant", "Fox",
                "Cow","Donkey","Monkey"};

        // Locate the ListView in listview_main.xml
        lv_noibat_phim = (ListView) findViewById(R.id.lv_phim_noibat);

        for (int i = 0; i < animalNameList.length; i++) {
            AnimalNames animalNames = new AnimalNames(animalNameList[i]);
            // Binds all strings into an array
            arraylist.add(animalNames);
        }

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(this, arraylist);

        // Binds the Adapter to the ListView
        lv_noibat_phim.setAdapter(adapter);

        // Locate the EditText in listview_main.xml
        SearchView searchView = (SearchView) findViewById(R.id.search_phim_activity);
        searchView.setOnQueryTextListener(this);
    }

    private void GetJsonYoutube(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonItems = response.getJSONArray("items");

                            String title="";
                            String url ="";
                            String idVideo ="";

                            for (int i=0; i<jsonItems.length(); i++){
                                JSONObject jsonItem = jsonItems.getJSONObject(i);

                                JSONObject jsonSnippet = jsonItem.getJSONObject("snippet");

                                title = jsonSnippet.getString("title");

                                JSONObject jsonThumbnail = jsonSnippet.getJSONObject("thumbnails");

                                JSONObject jsonMedium = jsonThumbnail.getJSONObject("medium");

                                url = jsonMedium.getString("url");

                                JSONObject jsonResourceID = jsonSnippet.getJSONObject("resourceId");

                                idVideo = jsonResourceID.getString("videoId");

                                arrayVideo.add(new Video_Phim(title, url, idVideo));

                                //Toast.makeText(Phim_Activity.this, idVideo, Toast.LENGTH_SHORT).show();
                            }

                            adapterPhim.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Phim_Activity.this, "loi", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }
}
