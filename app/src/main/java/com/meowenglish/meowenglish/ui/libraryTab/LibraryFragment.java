package com.meowenglish.meowenglish.ui.libraryTab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.meowenglish.meowenglish.data.Book;
import com.meowenglish.meowenglish.BooksRecyclerAdapter;
import com.meowenglish.meowenglish.R;

import java.util.ArrayList;

public class LibraryFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private TextView emptyLibraryText;
    private LibraryViewModel libraryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        libraryViewModel =
                new ViewModelProvider(this).get(LibraryViewModel.class);
        view = inflater.inflate(R.layout.fragment_library, container, false);

        recyclerView = view.findViewById(R.id.booksRecyclerView);
        emptyLibraryText = view.findViewById(R.id.emptyLibraryText);

        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Пример 0", new byte[0]));
        books.add(new Book("Пример 1", new byte[0]));
        showBooks(books);

        return view;
    }

    public void showBooks(ArrayList<Book> books)
    {
        if (books.size() <= 0)
        {
            recyclerView.setVisibility(View.GONE);
            emptyLibraryText.setVisibility(View.VISIBLE);
        }
        else
        {
            recyclerView.setVisibility(View.VISIBLE);
            emptyLibraryText.setVisibility(View.GONE);

            BooksRecyclerAdapter booksRecyclerAdapter = new BooksRecyclerAdapter(getContext(), books);
            recyclerView.setAdapter(booksRecyclerAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }
}