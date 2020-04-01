package com.mananpro.me.msearchapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddDetails extends AppCompatActivity {

    Button mBtn;
    TextView textView, backBtn;
    EditText number, url, pin;
    DatabaseReference reference;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);
        mBtn = findViewById(R.id.submit);
        textView = findViewById(R.id.textView);
        backBtn = findViewById(R.id.back);
        number = findViewById(R.id.numberinput);
        url = findViewById(R.id.urlinput);
        pin = findViewById(R.id.enterpin);
        final String pinText = pin.getText().toString();
        final String urlText = url.getText().toString();
        final String numberText = number.getText().toString();

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitRequest(pinText, urlText, numberText);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBack = new Intent(AddDetails.this, MainActivity.class);
                startActivity(goBack);
                finish();
            }
        });

        reference = FirebaseDatabase.getInstance().getReference().child("Data");

        String mGroupId = reference.getKey();
        Log.d("Group Id", mGroupId);

        // mBtn.setOnClickListener();
    }

    private void submitRequest(String pinno, String url, String number) {
        if (pinno == "Hello") {
            reference.child(number).child("name").setValue(number);
            reference.child(number).child("pic_url").setValue(url);

        }
        else {
            Toast.makeText(getApplicationContext(), getString(R.string.wrong_pin), Toast.LENGTH_LONG).show();
        }
    }
}
