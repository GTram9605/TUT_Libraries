package za.ac.nplinnovations.tutlibraries.menu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import za.ac.nplinnovations.tutlibraries.R;
import za.ac.nplinnovations.tutlibraries.adapters.AdapterBooks;
import za.ac.nplinnovations.tutlibraries.adapters.AdapterMessages;
import za.ac.nplinnovations.tutlibraries.entities.Book;
import za.ac.nplinnovations.tutlibraries.entities.Message;

public class LibrarianActivity extends AppCompatActivity {
    private ListView lvChats;
    private FirebaseDatabase database;
    private DatabaseReference messages;
    private List<Message> messageList;
    private String TAG = "LibraryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian);
        Toolbar toolbar = findViewById(R.id.toolbar);
        //toolbar.setTitle("Renew lease");
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Talk to librarian");
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.purple_500)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
        getWindow().setStatusBarColor(ContextCompat.getColor(this,  R.color.purple_500)); //status bar or the time bar at the top

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        messageList = new ArrayList<Message>();
        lvChats = (ListView) findViewById(R.id.lvChats);
        AdapterMessages adapter = new AdapterMessages(getBaseContext(),
                android.R.layout.simple_list_item_1, messageList);

        database = FirebaseDatabase.getInstance("https://tut-libraries-default-rtdb.firebaseio.com/");
        messages = database.getReference("messages");

        messages.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());
                // A comment has changed position, use the key to determine if we are
                // displaying this comment and if so move it.
                messageList.add(dataSnapshot.getValue(Message.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                messageList.add(dataSnapshot.getValue(Message.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                messageList.remove(dataSnapshot.getValue(Message.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                messageList.add(dataSnapshot.getValue(Message.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "postComments:onCancelled", databaseError.toException());
                Toast.makeText(getBaseContext(), "Failed to load messages.",
                        Toast.LENGTH_SHORT).show();
            }
        });
        lvChats.setAdapter(adapter);
    }

    public void onClickSend(View view) {
        EditText etMessage = (EditText)findViewById(R.id.etMessage);
        if (etMessage.getText().length() < 2){
            etMessage.setError("Cannot send blank text.");
        }else{
            messages.push().setValue(new Message("214219280", etMessage.getText().toString()
            , new SimpleDateFormat("dd/MM/yyyy HH:MM", Locale.getDefault()).format(new Date())
            , true));

        }
    }
}