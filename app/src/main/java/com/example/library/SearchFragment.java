package com.example.library;

import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;
import java.util.Set;


public class SearchFragment extends Fragment  {
    SharedPreferences sharedPrefernce;
    private RecyclerView rvBooks;
    private ProgressBar progressBar;
    private BookAdapter adapter;
    private MenuItem menuItem;
    private SearchView searchView;
    Toolbar toolbar;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sharedPrefernce = getContext().getSharedPreferences("veriler",Context.MODE_PRIVATE);
        String gelen = sharedPrefernce.getString("username","no login");

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        toolbar = view.findViewById(R.id.toolbar);


        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        progressBar = view.findViewById(R.id.progressBarId);
        progressBar.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
            }
        },1000);

        rvBooks = view.findViewById(R.id.rvBooks);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvBooks.setLayoutManager(layoutManager);
        rvBooks.setHasFixedSize(true);

        FirebaseRecyclerOptions<Books> options =
                new FirebaseRecyclerOptions.Builder<Books>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("books"),Books.class )
                        .build();

        adapter= new BookAdapter(options);
        rvBooks.setAdapter(adapter);

        setHasOptionsMenu(true);
        return view;
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



    @Override
   public void onCreateOptionsMenu(Menu menu,MenuInflater menuInflater){

       menuInflater.inflate(R.menu.menu_item,menu);
       menuItem= menu.findItem(R.id.search);
       searchView = (SearchView) menuItem.getActionView();
               //MenuItemCompat.getActionView(menuItem);
       searchView.setIconified(true);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query);
                return false;
            }
        });



      super.onCreateOptionsMenu(menu,menuInflater);
   }

   private void txtSearch(String str){

       FirebaseRecyclerOptions<Books> options =
               new FirebaseRecyclerOptions.Builder<Books>()
                       .setQuery(FirebaseDatabase.getInstance().getReference().child("books").orderByChild("book_name")
                               .startAt(str).endAt(str+"\uf8ff"),Books.class )
                       .build();

       adapter = new BookAdapter(options);
       adapter.startListening();
       rvBooks.setAdapter(adapter);

   }















}







