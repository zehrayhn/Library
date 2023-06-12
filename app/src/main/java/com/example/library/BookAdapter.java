package com.example.library;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class BookAdapter extends FirebaseRecyclerAdapter<Books, BookAdapter.BookViewHolder> {

    ProgressBar progressBar;
    private RecyclerView rvBooks;
    public BookAdapter(@NonNull FirebaseRecyclerOptions<Books> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull BookViewHolder holder, int position, @NonNull Books books) {

        holder.textViewTitle.setText(books.getBook_name());
        holder.textViewAuthor.setText(books.getAuthor());
        holder.textViewCategory.setText(books.getCategory());
        holder.imageViewBook.setImageResource(R.mipmap.library_icon_foreground);
        holder.imageButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

        Glide.with(holder.imageViewBook.getContext())
                .load(books.getBook_image())
                .into(holder.imageViewBook);

    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_books,parent,false);
        return new BookViewHolder(view);
    }
    public class BookViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewBook;
        TextView textViewTitle,textViewAuthor,textViewCategory;
        ImageButton imageButtonAdd, imageButtonAdded, imageButtonComment;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBarId);

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
    public void onDataChanged(){
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);

        }
    }
}


