package com.mananpro.me.msearchapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<Profile> list;
    MyAdapter adapter;
    SearchView searchView;
    FloatingActionButton addNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNew = findViewById(R.id.addNumbers);

        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddDetails.class);
                startActivity(intent);
            }
        });

        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.myRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        reference = FirebaseDatabase.getInstance().getReference().child("Data");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {

                    Profile p = dataSnapshot1.getValue(Profile.class);
                    list.add(p);

                }
                adapter = new MyAdapter(MainActivity.this, list);
                recyclerView.setAdapter(adapter);

                if (searchView != null) {
                    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            search(newText);
                            return true;
                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void search(String str) {
        ArrayList<Profile> myList = new ArrayList<>();

        for (Profile object : list) {
            if(object.getName().toLowerCase().contains(str.toLowerCase())) {
                myList.add(object);
            }
        }
        adapter = new MyAdapter(MainActivity.this, myList);
        recyclerView.setAdapter(adapter);
    }
}
