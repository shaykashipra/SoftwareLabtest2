package com.example.labtest_2_ui_ci;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Info {
    private int units;
    private String genreName;

    public Info(int units, String genreName) {
        this.units = units;
        this.genreName = genreName;
    }

    public int getUnits() {
        return units;
    }

    public String getGenreName() {
        return genreName;
    }
}

class Genre {
    private Map<String, List<Book>> booksByGenre;

    public Genre() {
        this.booksByGenre = new HashMap<>();
        booksByGenre.put("mystery", new ArrayList<>());
        booksByGenre.put("fantasy", new ArrayList<>());
        booksByGenre.put("thriller", new ArrayList<>());
    }

    public void addBook(Book book) {
        booksByGenre.get(book.getGenre()).add(book);
    }

    public List<Book> getBooksByGenre(String genre) {
        return booksByGenre.get(genre.toLowerCase());
    }
    public Map<String, List<Book>> getAllBooks() {
        return booksByGenre;
    }
}

class Book {
    private String title;
    private String author;
    private String genre;
    private int units;

    public Book(String title, String author, String genre, int units) {
        this.title = title;
        this.author = author;
        this.genre = genre.toLowerCase();
        this.units = units;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getUnits() {
        return units;
    }

    public void borrowBook(int borrowedUnits) {
        units -= borrowedUnits;
    }
}



public class MainActivity extends AppCompatActivity {
    EditText genreEditText, titleEditText, authorEditText, unitsEditText,next;
   Genre genre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        genreEditText = findViewById(R.id.editTextText4);
        titleEditText = findViewById(R.id.editTextText5);
        authorEditText = findViewById(R.id.editTextText6);
        unitsEditText = findViewById(R.id.editTextText7);

        genre = new Genre();

        Button addBookButton = findViewById(R.id.button2);

        Button  next=findViewById(R.id.button3);
        addBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBook();
            }
        });

        Button borrowBookButton = findViewById(R.id.button);
        borrowBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrowBook();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, MainActivity2.class);
                i.putExtra("genreMap", (HashMap<String, List<Book>>) genre.getAllBooks());
startActivity(i);
            }
        });
    }

    private void addBook() {
        String genreStr = genreEditText.getText().toString();
        String title = titleEditText.getText().toString();
        String author = authorEditText.getText().toString();
        int units = Integer.parseInt(unitsEditText.getText().toString());

        Book book = new Book(title, author, genreStr, units);
        genre.addBook(book);

        Toast.makeText(MainActivity.this, "Book added", Toast.LENGTH_SHORT).show();
    }

    private void borrowBook() {
        String title = titleEditText.getText().toString();
        int units = Integer.parseInt(unitsEditText.getText().toString());

        List<Book> books = genre.getBooksByGenre(title);
        if (books != null && !books.isEmpty()) {
            Book book = books.get(0);
            book.borrowBook(units);
        } else {
            Toast.makeText(MainActivity.this, "Book not found", Toast.LENGTH_SHORT).show();
        }
    }

}


