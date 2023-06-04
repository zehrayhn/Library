package com.example.library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BooksViewHolder> {

    private Context context;
    private ArrayList<Books> booksList;

    public BookAdapter(Context context, ArrayList<Books> booksList) {
        this.context = context;
        this.booksList = booksList;
    }

    public class BooksViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageViewBook;
        public TextView textViewTitle,textViewAuthor,textViewCategory;
        public ImageButton imageButtonAdd, imageButtonAdded, imageButtonComment;


        public BooksViewHolder (@NonNull View itemView) {
            super(itemView);
            imageViewBook = itemView.findViewById(R.id.imageViewBook);
            textViewTitle = itemView.findViewById(R.id.textViewtitle);
            textViewAuthor= itemView.findViewById(R.id.textViewAuthor);
            textViewCategory= itemView.findViewById(R.id.textViewCategory);
            imageButtonAdd = itemView.findViewById(R.id.imageButtonAdd);
            imageButtonAdded = itemView.findViewById(R.id.imageButtonAdded);
            imageButtonComment= itemView.findViewById(R.id.imageButtonComment);
        }
    }

    @NonNull
    @Override
    public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_books,parent,false);
        return new BooksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.BooksViewHolder holder, int position) {
        Books books = booksList.get(position);
        holder.textViewTitle.setText(books.getBook_name());
        holder.textViewAuthor.setText(books.getAuthor());
        holder.textViewCategory.setText(books.getCategory());
        holder.imageViewBook.setImageResource(R.mipmap.library_icon_foreground);
        holder.imageButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,books.getBook_name(),Toast.LENGTH_SHORT);
            }
        });

        holder.imageButtonAdded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
    });

        holder.imageButtonComment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });



    }



    @Override
    public int getItemCount() {
        return booksList.size();
    }


}
