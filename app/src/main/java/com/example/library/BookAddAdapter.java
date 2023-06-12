package com.example.library;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class BookAddAdapter extends FirebaseRecyclerAdapter<Books, BookAddAdapter.BookViewHolder> {


    public BookAddAdapter(@NonNull FirebaseRecyclerOptions<Books> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull BookViewHolder holder, int position, @NonNull Books books) {

        holder.textViewTitle.setText(books.getBook_name());
        holder.textViewAuthor.setText(books.getAuthor());
        holder.textViewCategory.setText(books.getCategory());
        holder.imageViewBook.setImageResource(R.mipmap.library_icon_foreground);
        holder.imageButtonComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


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
                .inflate(R.layout.row_add_books,parent,false);
        return new BookViewHolder(view);
    }
    public class BookViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewBook;
        TextView textViewTitle,textViewAuthor,textViewCategory;
        ImageButton  imageButtonComment;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewBook = (ImageView)itemView.findViewById(R.id.imageViewBook);
            textViewTitle = (TextView)itemView.findViewById(R.id.textViewtitle);
            textViewAuthor= (TextView)itemView.findViewById(R.id.textViewAuthor);
            textViewCategory= (TextView)itemView.findViewById(R.id.textViewCategory);
            imageButtonComment= (ImageButton)itemView.findViewById(R.id.imageButtonComment);

        }
    }
}


