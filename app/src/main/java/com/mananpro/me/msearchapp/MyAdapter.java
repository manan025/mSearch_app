package com.mananpro.me.msearchapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Profile> profiles;

    public MyAdapter(Context c, ArrayList<Profile> p) {
        context = c;
        profiles = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.name.setText(profiles.get(position).getName());
        // Picasso.get().load(profiles.get(position).getProfilePic()).into(holder.profilePic);
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, search_answer.class);
                i.putExtra("url", profiles.get(position).getPic_url());
                i.putExtra("number", profiles.get(position).getName());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        CardView parentLayout;

        private MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
