package za.ac.nplinnovations.tutlibraries.menu.lease;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import za.ac.nplinnovations.tutlibraries.R;
import za.ac.nplinnovations.tutlibraries.entities.Lease;
import za.ac.nplinnovations.tutlibraries.menu.MainMenuActivity;

public class RenewLeaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renew_lease);
        Toolbar toolbar = findViewById(R.id.toolbar);
        //toolbar.setTitle("Renew lease");
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Renew lease");

        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.purple_500)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
        getWindow().setStatusBarColor(ContextCompat.getColor(this,  R.color.purple_500)); //status bar or the time bar at the top

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button button = (Button)findViewById(R.id.btnRenewButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                renewLease(v);
            }
        });

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

    private void renewLease(View view){
        String newStartDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date()),
            newEndDate;
        ((TextView)findViewById(R.id.tvStartdate)).
                setText(newStartDate);

        int noOfDays = 21; //i.e two weeks
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(calendar.getTime());
        calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
        newEndDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.getTime());
        ((TextView)findViewById(R.id.tvEnddate)).
                setText(newEndDate);

        updateOnDB(newStartDate, newEndDate);
    }


    private void updateOnDB(String newStartDate, String newEndDate) {
        DatabaseReference lease = FirebaseDatabase.getInstance().getReference("");
        lease.push().setValue(new Lease(new Random().nextInt(99999999),
                "9293823878328","214219280", newStartDate, newEndDate));

        new AlertDialog.Builder(RenewLeaseActivity.this)
                .setTitle("Lease renewed")
                .setMessage("New lease period starts from " + newStartDate + " until " + newEndDate)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getBaseContext(), MainMenuActivity.class));
                    }
                }).show();
    }
}