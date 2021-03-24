package za.ac.nplinnovations.tutlibraries.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

import za.ac.nplinnovations.tutlibraries.R;
import za.ac.nplinnovations.tutlibraries.menu.MainMenuActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClickLogin(View view) {
        if (formValidated())
        startActivity(new Intent(view.getContext(), MainMenuActivity.class));
    }

    private boolean formValidated() {
        TextInputEditText tiUsername = (TextInputEditText)findViewById(R.id.etUsername)
                , tiPassword = (TextInputEditText)findViewById(R.id.etPassword);

        if (tiUsername.getText().toString().isEmpty()){
            tiUsername.setError("This field is required.");
        } else if(tiUsername.getText().length() <= 4){
            tiUsername.setError("Valid username is required");
        } else if (tiPassword.getText().toString().isEmpty()){
            tiPassword.setError("This field is required.");
        } else if(tiPassword.getText().length() <= 4){
            tiPassword.setError("Password too short.");
        }else
            return true;

        return false;
    }

    public void onClickRegister(View view) {
        startActivity(new Intent(view.getContext(), RegistrationActivity.class));
    }
}