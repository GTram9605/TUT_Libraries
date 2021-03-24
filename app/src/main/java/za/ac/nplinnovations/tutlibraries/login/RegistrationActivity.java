package za.ac.nplinnovations.tutlibraries.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

import za.ac.nplinnovations.tutlibraries.R;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void onClickLogin(View view) {
        startActivity(new Intent(view.getContext(), LoginActivity.class));
    }

    public void onClickRegister(View view) {
        if(formValidated())
            startActivity(new Intent(view.getContext(), LoginActivity.class));
    }

    private boolean formValidated() {
        TextInputEditText tiStudentno = (TextInputEditText)findViewById(R.id.etStudentNumber)
            , tiUsername = (TextInputEditText)findViewById(R.id.etUsername)
            , tiPassword = (TextInputEditText)findViewById(R.id.etPassword)
            , tiConfirmPassword = (TextInputEditText)findViewById(R.id.etConfirmPassword);

        if (tiStudentno.getText().toString().isEmpty()){
            tiStudentno.setError("This field is required.");
        } else if(tiStudentno.getText().length() <= 4){
            tiStudentno.setError("Valid student number is required");
        }else if (tiUsername.getText().toString().isEmpty()){
            tiUsername.setError("This field is required.");
        } else if(tiUsername.getText().length() <= 4){
            tiUsername.setError("Valid username is required");
        } else if (tiPassword.getText().toString().isEmpty()){
            tiPassword.setError("This field is required.");
        } else if(tiPassword.getText().length() <= 4){
            tiPassword.setError("Password too short.");
        }else if (tiConfirmPassword.getText().toString().isEmpty()){
            tiConfirmPassword.setError("This field is required.");
        } else if(tiConfirmPassword.getText().toString().compareTo(tiPassword.getText().toString()) > 0){
            tiConfirmPassword.setError("Passwords must match.");
        }else
            return true;

        return false;
    }


}