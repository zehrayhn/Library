package com.example.library;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class SearchFragment extends Fragment {
    private RecyclerView rvBooks;
    Context context;
    private ArrayList<Books> booksArrayList;
    private BookAdapter bookAdapter;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_search, container, false);

        rvBooks = view.findViewById(R.id.rvBooks);
        rvBooks.setHasFixedSize(true);
        Books b1 = new Books(1,"ş"," ","l","w");

        booksArrayList = new ArrayList<>();
        booksArrayList.add(b1);
        bookAdapter = new BookAdapter(context,booksArrayList);



        return view;

    }


}