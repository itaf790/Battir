package com.project.battirtourguideapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class menus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus);

        ImageView imgView = findViewById(R.id.menu_image);

        Intent intent = getIntent();;
        String url = intent.getStringExtra("url3");

        //Loading image from Glide library.
        Glide.with(this).load(url).into(imgView);


    }
}


