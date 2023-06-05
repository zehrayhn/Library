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

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private static final String Tag="RecyclerView";

    private Context context;
    private ArrayList<Books> booksArrayList;

    public BookAdapter(Context context, ArrayList<Books> booksArrayList) {
        this.context = context;
        this.booksArrayList = booksArrayList;
    }

    class BookViewHolder extends RecyclerView.ViewHolder{

        ImageView imageViewBook;
        TextView textViewTitle,textViewAuthor,textViewCategory;
        ImageButton imageButtonAdd, imageButtonAdded, imageButtonComment;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewBook = (ImageView)itemView.findViewById(R.id.imageViewBook);
            textViewTitle = (TextView)itemView.findViewById(R.id.textViewtitle);
            textViewAuthor= (TextView)itemView.findViewById(R.id.textViewAuthor);
            textViewCategory= (TextView)itemView.findViewById(R.id.textViewCategory);
            imageButtonAdd = (ImageButton)itemView.findViewById(R.id.imageButtonAdd);
            imageButtonAdded = (ImageButton)itemView.findViewById(R.id.imageButtonAdded);
            imageButtonComment= (ImageButton)itemView.findViewById(R.id.imageButtonComment);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        holder.textViewTitle.setText(booksArrayList.get(position).getBook_name());
        holder.textViewAuthor.setText(booksArrayList.get(position).getAuthor());
        holder.textViewCategory.setText(booksArrayList.get(position).getCategory());
        holder.imageViewBook.setImageResource(R.mipmap.library_icon_foreground);
        holder.imageButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

        Glide.with(holder.imageViewBook.getContext())
                .load(booksArrayList.get(position).getBook_image())
                .into(holder.imageViewBook);
    }

    @NonNull
    @Override
    public BookAdapter.BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_books,parent,false);
        return new BookViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return booksArrayList.size();
    }




}
