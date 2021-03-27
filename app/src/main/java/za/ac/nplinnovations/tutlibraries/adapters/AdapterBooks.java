package za.ac.nplinnovations.tutlibraries.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import za.ac.nplinnovations.tutlibraries.R;
import za.ac.nplinnovations.tutlibraries.entities.Book;
import za.ac.nplinnovations.tutlibraries.entities.Lease;
import za.ac.nplinnovations.tutlibraries.menu.MainMenuActivity;

public class AdapterBooks extends ArrayAdapter {
    private Context mContext;
    private List<Book> mList;
    private FirebaseDatabase database;
    private DatabaseReference leases;
    private List<Lease> mLeasesList;

    public AdapterBooks(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        mContext = context;
        mList = objects;
        database = FirebaseDatabase.getInstance("https://tut-libraries-default-rtdb.firebaseio.com/");
        leases = database.getReference("lease");
        mLeasesList = new ArrayList<>();
        leases.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                mLeasesList.add(dataSnapshot.getValue(Lease.class));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                mLeasesList.add(dataSnapshot.getValue(Lease.class));
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                mLeasesList.add(dataSnapshot.getValue(Lease.class));
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                mLeasesList.add(dataSnapshot.getValue(Lease.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        if (view == null)
            view = LayoutInflater.from(mContext).inflate(R.layout.layout_book, null);

        ImageView ivBookimage = (ImageView)view.findViewById(R.id.ivBookimage);
        TextView tvTitle = (TextView)view.findViewById(R.id.tvTitle),
            tvAuthor = (TextView)view.findViewById(R.id.tvAuthor),
            tvISBN = (TextView)view.findViewById(R.id.tvISBN),
            tvPages = (TextView)view.findViewById(R.id.tvPages);

        Book b = mList.get(position);

        Picasso.get().load(b.getImage()).into(ivBookimage, new Callback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onError(Exception e) {
                ivBookimage.setImageDrawable(mContext.getDrawable(R.drawable.noimage));
                Log.e("BooksActivity", e.getLocalizedMessage());
            }
        });
        tvTitle.setText(b.getTitle());
        tvAuthor.setText(b.getAuthor());
        tvISBN.setText(b.getIsbn());
        tvPages.setText(b.getPages());

        ((Button)view.findViewById(R.id.btnLeasebook)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkIfBookAlreadyLease(b)){
                    int noOfDays = 21; //i.e two weeks
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(calendar.getTime());
                    calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
                    leases.push().setValue(new Lease((new Random()).nextInt(99999999), b.getIsbn(),"214219280",
                            new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date()),
                            new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.getTime())));

                    Toast.makeText(v.getContext(), b.getTitle() + " has been booked, and will be delivered to you within 2-3 working days.", Toast.LENGTH_LONG).show();
                    mContext.startActivity(new Intent(mContext, MainMenuActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }else
                    Toast.makeText(v.getContext(), "User already leased book.", Toast.LENGTH_SHORT).show();


            }
        });

        return view;
    }

    private boolean checkIfBookAlreadyLease(Book b) {
        for (int i = 0; i < mLeasesList.size(); i++){
            if (mLeasesList.get(i).getIsbn() == b.getIsbn() && mLeasesList.get(i).getStudent_no().equalsIgnoreCase("214219280"))
                return true;
        }

        return false;
    }
}
