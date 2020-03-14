package com.example.battirtourguideapp;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

abstract class FirebaseRecyclerAdapter<T, T1> extends RecyclerView.Adapter {
    public FirebaseRecyclerAdapter(Class<T> modelClass, int row, Class<T1> viewHolderClass, DatabaseReference mRef) {
    }

    protected abstract void populateViewHolder(ViewHolder viewHolder, Model model, int position);

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
