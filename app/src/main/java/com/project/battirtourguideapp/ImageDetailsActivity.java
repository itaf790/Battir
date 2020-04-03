package com.project.battirtourguideapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class ImageDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);

        ImageView imgView = findViewById(R.id.details_image_View);
        TextView imgDesc = findViewById(R.id.details_image_desc);

        Intent intent = getIntent();;
        String desc = intent.getStringExtra("desc");
        String url = intent.getStringExtra("url2");

        //Loading image from Glide library.
        Glide.with(this).load(url).into(imgView);

        //Set image description
        imgDesc.setText(desc);
    }
}
