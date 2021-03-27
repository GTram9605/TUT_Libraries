package za.ac.nplinnovations.tutlibraries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import za.ac.nplinnovations.tutlibraries.entities.Book;
import za.ac.nplinnovations.tutlibraries.entities.TUTStudent;
import za.ac.nplinnovations.tutlibraries.login.LoginActivity;

public class MainActivity extends AppCompatActivity {
    private ProgressBar loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingBar = (ProgressBar) findViewById(R.id.loadingBar);
        Thread th1 = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++){
                    try {
                        loadingBar.setProgress(i);
                        sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //loadData();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        };

        th1.start();
    }

    private void loadData(){
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://tut-libraries-default-rtdb.firebaseio.com/");
        DatabaseReference books, registeredStudents;

        books = database.getReference("books");
        for(int i = 0; i < loadBooks().size(); i++){
            books.push().setValue(loadBooks().get(i));
        }

        registeredStudents = database.getReference("users");
        for(int i = 0; i < loadRegisteredStudents().size(); i++){
            registeredStudents.push().setValue(loadRegisteredStudents().get(i));
        }
    }

    private ArrayList<Book> loadBooks (){
        ArrayList<Book> books_list = new ArrayList<>();
        books_list.add(new Book("9780141033570","Thinking, Fast and Slow", "Daniel Kahneman",
    "150", "https://img.exclusivebooks.co.za/biblio/thumbnail.php?isbn=9780141033570&w=280&h=450&rc=1"));
        books_list.add(new Book("9780241491515","A Promised Land", "Barack Obama",
                "136", "https://img.exclusivebooks.co.za/biblio/thumbnail.php?isbn=9780241491515&w=280&h=450&rc=1"));
        books_list.add(new Book("9781776191390","The Stellenbosch Mafia", "Pieter du Toit",
                "240", "https://img.exclusivebooks.co.za/biblio/thumbnail.php?isbn=9781776191390&w=280&h=450&rc=1"));
        books_list.add(new Book("9780062641540","The Subtle Art of Not Giving A F*ck", "Mark Manson",
                "211", "https://img.exclusivebooks.co.za/biblio/thumbnail.php?isbn=9780062641540&w=280&h=450&rc=1"));
        books_list.add(new Book("9781847941831","Atomic Habits", "James Clear",
                "196", "https://img.exclusivebooks.co.za/biblio/thumbnail.php?isbn=9781847941831&w=280&h=450&rc=1"));

        return books_list;
    }

    private ArrayList<TUTStudent> loadRegisteredStudents(){
        ArrayList<TUTStudent> tutStudents = new ArrayList<>();
        tutStudents.add(new TUTStudent("214219280", "Thabiso", "Ramolobeng",
        "thabisoramolobeng@gmail.com", "Btech: Information Technology", "7820, Bela Bela, Limpopo"));
        tutStudents.add(new TUTStudent("2105448552", "Thato", "Mokgose",
                "tmokgosi@gmail.com", "Btech: Software Engineering", "25841, Soshanguve, Gauteng"));

        return tutStudents;
    }

}