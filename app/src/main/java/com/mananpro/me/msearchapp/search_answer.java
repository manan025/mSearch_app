package com.mananpro.me.msearchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class search_answer extends AppCompatActivity {

    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_answer);

        imageView = findViewById(R.id.image_answer);
        textView = findViewById(R.id.number);
        // Picasso.get().load(url).into(imageView);
        checkIntent();
    }

    private void checkIntent() {
        if (getIntent().hasExtra("url")) {

            String image_url = getIntent().getStringExtra("url");
            String image_no = getIntent().getStringExtra("number");
            setNumber(image_no);
            setImage(image_url);

        }
    }

    private void setImage(String image_url) {
        Picasso.get().load(image_url).into(imageView);
    }

    private void setNumber(String image_no) {
        textView.setText(image_no);
    }
}
