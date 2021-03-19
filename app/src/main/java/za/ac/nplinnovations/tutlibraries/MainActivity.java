package za.ac.nplinnovations.tutlibraries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

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

                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        };

        th1.start();
    }
}