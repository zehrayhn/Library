package com.example.library;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class SearchFragment extends Fragment {

    Context context;
    View view;
    private RecyclerView rvBooks;
    private ArrayList<Books> booksArrayList;
    private BookAdapter adapter;
    private DatabaseReference myRef;
    private FirebaseDatabase database;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_search, container, false);



        rvBooks = view.findViewById(R.id.rvBooks);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        rvBooks.setLayoutManager(layoutManager);
        rvBooks.setHasFixedSize(true);

        myRef = FirebaseDatabase.getInstance().getReference();

        booksArrayList=new ArrayList<>();

        ClearAll();

        GetDataFromFirebase();

        return view;
    }

    private void ClearAll() {

        if(booksArrayList != null){
            booksArrayList.clear();

            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }

        booksArrayList = new ArrayList<>();
    }

    private void GetDataFromFirebase() {


        Query query = myRef.child("books");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Books books = new Books();
                    books.setBook_image(snapshot.child("book_image").getValue().toString());
                    books.setBook_name(snapshot.child("book_name").getValue().toString());
                    books.setAuthor(snapshot.child("author").getValue().toString());
                    books.setCategory(snapshot.child("category").getValue().toString());

                    booksArrayList.add(books);
                }

                adapter = new BookAdapter(context,booksArrayList);
                rvBooks.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }




}


