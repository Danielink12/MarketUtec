package com.alas.mutec;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.Objects;

public class ImagenFullScreen extends AppCompatActivity {

    ImageView imageView;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_imagen_full_screen);
        Objects.requireNonNull(getSupportActionBar()).hide();

        Bundle geturl = getIntent().getExtras();
        url = geturl.getString("url");

        imageView = findViewById(R.id.imageFS);

        Picasso.get().load(url).into(imageView);
    }
}