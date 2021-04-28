package com.meowenglish.meowenglish.ui.libraryTab;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.meowenglish.meowenglish.R;
import com.meowenglish.meowenglish.WordFrequenceActivity;
import com.meowenglish.meowenglish.data.Book;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class BooksRecyclerAdapter extends RecyclerView.Adapter<BooksRecyclerAdapter.ViewHolder>
{
    private ArrayList<Book> books;
    private Context context;


    public  BooksRecyclerAdapter(Context context, ArrayList<Book> books)
    {
        this.context = context;
        this.books = books;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.book_item, parent, false);

        return new ViewHolder(view, books.get(books.size() - 1));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = books.get(position);

        byte[] bookCoverImageBytes = book.getCoverImage();
        if (bookCoverImageBytes.length <= 0)
        {
            Drawable defaulBookImage = context.getResources().getDrawable(R.drawable.book);
            holder.bookCoverImage.setImageDrawable(defaulBookImage);
        }
        else
        {
            holder.bookCoverImage.setImageBitmap(convertBytesToBitmap(book.getCoverImage()));
        }
        holder.bookTitle.setText(book.getTitle());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    private Bitmap convertBytesToBitmap(byte[] bytes)
    {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
    private byte[] convertBitmapToBytes(Bitmap bitmap)
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView bookCoverImage;
        TextView bookTitle;
        Book book;


        public ViewHolder(@NonNull View itemView, Book book) {
            super(itemView);

            itemView.setOnClickListener(this);

            this.book = book;

            bookCoverImage = itemView.findViewById(R.id.bookCoverImage);
            bookTitle = itemView.findViewById(R.id.bookTitle);
        }


        @Override
        public void onClick(View v) {
            Intent wordFrequenceIntent = new Intent(context, WordFrequenceActivity.class);
            wordFrequenceIntent.putExtra(Intent.EXTRA_TEXT, bookTitle.getText());
            wordFrequenceIntent.putExtra("BOOK", book);

            context.startActivity(wordFrequenceIntent);
        }
    }
}
