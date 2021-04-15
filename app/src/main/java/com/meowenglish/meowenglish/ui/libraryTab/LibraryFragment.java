package com.meowenglish.meowenglish.ui.libraryTab;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.meowenglish.meowenglish.data.Book;
import com.meowenglish.meowenglish.R;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class LibraryFragment extends Fragment {
    private final int GET_BOOK_REQUEST_CODE = 0;

    private View view;
    private RecyclerView recyclerView;
    private TextView emptyLibraryText;
    private LibraryViewModel libraryViewModel;

    ArrayList<Book> books;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        libraryViewModel =
                new ViewModelProvider(this).get(LibraryViewModel.class);
        view = inflater.inflate(R.layout.fragment_library, container, false);

        recyclerView = view.findViewById(R.id.booksRecyclerView);
        emptyLibraryText = view.findViewById(R.id.emptyLibraryText);

        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        books = new ArrayList<>();
        books.add(new Book("Пример 0", new byte[0]));
        books.add(new Book("Пример 1", new byte[0]));
        showBooks(books);

        FloatingActionButton addNewBookButton = view.findViewById(R.id.addBookButton);
        addNewBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent, GET_BOOK_REQUEST_CODE);
            }
        });

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == GET_BOOK_REQUEST_CODE)
        {
            String lastPathSegment = data.getData().getLastPathSegment();
            String filePath = data.getData().getPath();

            int lastDotIndex = filePath.lastIndexOf('.');
            String fileExtension = filePath.substring(lastDotIndex + 1);

            int lastSlashIndex = filePath.indexOf('/');
            String fileName = filePath.substring(lastSlashIndex + 1, lastDotIndex - lastSlashIndex);

            String toastMessage = "В библиотеку добавлена новая книга";

            switch (fileExtension)
            {
                case "epub":
                {
                    Book newBook;
                    //Read book details:
                    newBook = new Book(fileName, new byte[0]);

                    //Analyse the book:

                    //Add to library:
                    books.add(newBook);

                    break;
                }
                default:
                {
                    toastMessage = "Книги с таким расширением не поддерживаются";

                    break;
                }
            }

            Toast.makeText(getActivity(), toastMessage, Toast.LENGTH_LONG).show();

            showBooks(books);
        }
    }
}