package za.ac.nplinnovations.tutlibraries.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import za.ac.nplinnovations.tutlibraries.R;

public class PayFinesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_fines);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Pay fines");

        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.purple_500)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
        getWindow().setStatusBarColor(ContextCompat.getColor(this,  R.color.purple_500)); //status bar or the time bar at the top

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    public void onClickPayFine(final View view) {

        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.layout_credit_info, null);

        final EditText etCrdNo = (EditText)dialogView.findViewById(R.id.etCardNo),
                etCvv = (EditText)dialogView.findViewById(R.id.etCVTNo),
                etMMYY = (EditText)dialogView.findViewById(R.id.etMMYY),
                etAmount = (EditText)dialogView.findViewById(R.id.etDonationAmount);

        etAmount.setText("30.00");

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("Online Payment");
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){


                String cardNo = etCrdNo.getText().toString(),
                        cvv = etCvv.getText().toString(),
                        mmYY = etMMYY.getText().toString(),
                        amtDueString = etAmount.getText().toString();

                double amount = Double.parseDouble(amtDueString);
                processPayment(cardNo, cvv, mmYY, amount, view);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setView(dialogView);
        builder.setCancelable(false);
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void processPayment(String cardNo, String cvv, String mmYY, double amount, View view) {
        
    }
}