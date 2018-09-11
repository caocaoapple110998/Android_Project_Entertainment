package com.example.asus.project_entertainment.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.asus.project_entertainment.Fragment.Fragment_TrangChu;
import com.example.asus.project_entertainment.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class PlayVideo_Activity_TrangChu extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    YouTubePlayerView youTubePlayerView;
    String idtt ="";
    int REQUEST_VIDEO = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video___trang_chu);

        youTubePlayerView = (YouTubePlayerView)findViewById(R.id.myYoutobe_TrangChu);


        Intent intent = getIntent();
        idtt = intent.getStringExtra("idVideoYoutobe");
        youTubePlayerView.initialize(Fragment_TrangChu.API_KEY, this);

       //Toast.makeText(this, idtt, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo(idtt);
//        youTubePlayer.setFullscreen(true);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(PlayVideo_Activity_TrangChu.this, REQUEST_VIDEO);
        }else {
            Toast.makeText(this, "Loi", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_VIDEO){
            youTubePlayerView.initialize(Fragment_TrangChu.API_KEY, PlayVideo_Activity_TrangChu.this);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
