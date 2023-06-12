package com.example.library;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class ProfileFragment extends Fragment {

    private RecyclerView rvAddBooks;
    private BookAddAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        rvAddBooks = view.findViewById(R.id.rvAddBooks);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvAddBooks.setLayoutManager(layoutManager);

        FirebaseRecyclerOptions<Books> options =
                new FirebaseRecyclerOptions.Builder<Books>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("books_add"),Books.class )
                        .build();

        adapter= new BookAddAdapter(options);
        rvAddBooks.setAdapter(adapter);


        return  view;


    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }



}