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

        video.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video1));

        MediaController media = new MediaController(this);

        video.setMediaController(media);

        video.start();

        /*Uri uri = Uri.parse("http://techslides.com/demos/sample-videos/small.mp4");

        video.setMediaController(new MediaController(this));

        video.setVideoURI(uri);

        video.requestFocus();

        video.start();*/

    }
}
