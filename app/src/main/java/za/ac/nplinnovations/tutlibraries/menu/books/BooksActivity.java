package za.ac.nplinnovations.tutlibraries.menu.books;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import za.ac.nplinnovations.tutlibraries.R;
import za.ac.nplinnovations.tutlibraries.adapters.AdapterBooks;
import za.ac.nplinnovations.tutlibraries.entities.Book;

public class BooksActivity extends AppCompatActivity {
    private ListView lvBooks;
    private FirebaseDatabase database;
    private DatabaseReference books;
    private String TAG = "BooksActivity";
    private ArrayList<Book> books_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        Toolbar toolbar = findViewById(R.id.toolbar);
        //toolbar.setTitle("Renew lease");
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Book catalogue");

        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.purple_500)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
        getWindow().setStatusBarColor(ContextCompat.getColor(this,  R.color.purple_500)); //status bar or the time bar at the top

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        books_list = new ArrayList<Book>();
        lvBooks = (ListView) findViewById(R.id.lvBooks);
        AdapterBooks adapter = new AdapterBooks(getBaseContext(),
                android.R.layout.simple_list_item_1, books_list);

        database = FirebaseDatabase.getInstance("https://tut-libraries-default-rtdb.firebaseio.com/");
        books = database.getReference("books");

        books.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());
                // A comment has changed position, use the key to determine if we are
                // displaying this comment and if so move it.
                books_list.add(dataSnapshot.getValue(Book.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());
                // A comment has changed position, use the key to determine if we are
                // displaying this comment and if so move it.
                books_list.add(dataSnapshot.getValue(Book.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());
                // A comment has changed position, use the key to determine if we are
                // displaying this comment and if so move it.
                books_list.add(dataSnapshot.getValue(Book.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());
                // A comment has changed position, use the key to determine if we are
                // displaying this comment and if so move it.
                books_list.add(dataSnapshot.getValue(Book.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "postComments:onCancelled", databaseError.toException());
                Toast.makeText(getBaseContext(), "Failed to load books.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        lvBooks.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
        }
        return true;
    }
}