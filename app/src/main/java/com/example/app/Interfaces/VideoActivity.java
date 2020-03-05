package com.example.app.Interfaces;

import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;
import com.example.app.R;

public class VideoActivity extends AppCompatActivity {

    VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_video);

        video = (VideoView) findViewById(R.id.videoPromocion);

        Uri uri = Uri.parse("http://techslides.com/demos/sample-videos/small.mp4");

        video.setMediaController(new MediaController(this));

        video.setVideoURI(uri);

        video.requestFocus();

        video.start();

    }
}
