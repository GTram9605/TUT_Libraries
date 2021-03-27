package za.ac.nplinnovations.tutlibraries.menu.fines;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import za.ac.nplinnovations.tutlibraries.R;
import za.ac.nplinnovations.tutlibraries.menu.MainMenuActivity;

public class PayFinesActivity extends AppCompatActivity {
    Gson gson = new GsonBuilder().setLenient().create();
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(250, TimeUnit.SECONDS)
            .connectTimeout(250, TimeUnit.SECONDS)
            .build();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Connection.PROTOCOL + Connection.IP_ADDRESS)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build();
    InterfacePost interfacePost = retrofit.create(InterfacePost.class);


    private androidx.appcompat.app.AlertDialog alert;

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
        Call<ResponsePayment> response = interfacePost.processPayment("Fine payment","Late return of book",
                cardNo, cvv, mmYY, "Test", "User","ststestacount@gmail.com", amount);
        response.enqueue(new Callback<ResponsePayment>() {
            @Override
            public void onResponse(Call<ResponsePayment> call, Response<ResponsePayment> response) {
                if (response.isSuccessful()){
                    displayPaymentSuccessMessage();
                }if(response.message().equalsIgnoreCase("conflict")){
                    displayCardDeclined();
                }else {
                    displayPaymentErrorMessage(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponsePayment> call, Throwable t) {
                //displayPaymentErrorMessage(t.getLocalizedMessage());
                displayPaymentErrorMessage("Unsuccesful, please check internet connectivity.");
            }
        });
    }

    private void displayCardDeclined() {
        Snackbar.make(getWindow().getDecorView(), "Card declined", Snackbar.LENGTH_LONG).show();
    }

    private void displayPaymentErrorMessage(String message) {
        Snackbar.make(getWindow().getDecorView(), message, Snackbar.LENGTH_INDEFINITE).show();
        Log.e("PayFinesActivity", message);
    }

    private void displayPaymentSuccessMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PayFinesActivity.this);
        builder.setTitle("Payment results");
        builder.setMessage("Thank you for paying your fine." );
        builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(PayFinesActivity.this, MainMenuActivity.class);
                startActivity(intent);
            }
        });
        builder.setCancelable(false);
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void showProgress(boolean status){
        if(alert == null){
            LayoutInflater inflater = this.getLayoutInflater();
            final View view = inflater.inflate(R.layout.loading_activity, null);
            androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setView(view);
            alert = builder.create();
        }

        if (status == true)
            alert.show();
        else
            alert.dismiss();
    }
}