package com.example.library;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SearchFragment extends Fragment {

   View view;
    private RecyclerView rvBooks;
    private RequestQueue requestQueue;
    private ArrayList<Books> booksList;
    private Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_search, container, false);


        rvBooks = view.findViewById(R.id.rvBooks);
        rvBooks.setHasFixedSize(true);
        rvBooks.setLayoutManager(new LinearLayoutManager(context));
        requestQueue = VolleySingleton.getmInstance(context).getRequestQueue();

        booksList = new ArrayList<Books>();
        fetchBooks();




        return view;
    }

    private void fetchBooks() {

        String url = "https://api.collectapi.com/book/newBook";

        StringRequest request = new StringRequest( url,  new Response.Listener<String>()

        {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int k = 0; k < jsonArray.length(); k++) {
                        JSONObject gelen = jsonArray.getJSONObject(k);
                        int id = gelen.getInt("id");
                        String first_name = gelen.getString("first_name");
                        String last_name = gelen.getString("last_name");
                        String email = gelen.getString("email");
                        String avatar = gelen.getString("avatar");


                        Books books = new Books(id, first_name, last_name, email, avatar);
                        booksList.add(books);
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                BookAdapter adapter = new BookAdapter(context,booksList);
                rvBooks.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(request);

    }



    }


