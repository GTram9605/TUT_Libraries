package za.ac.nplinnovations.tutlibraries.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import za.ac.nplinnovations.tutlibraries.R;
import za.ac.nplinnovations.tutlibraries.menu.MainMenuActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClickLogin(View view) {
        startActivity(new Intent(view.getContext(), MainMenuActivity.class));
    }

    public void onClickRegister(View view) {
        startActivity(new Intent(view.getContext(), RegistrationActivity.class));
    }
}