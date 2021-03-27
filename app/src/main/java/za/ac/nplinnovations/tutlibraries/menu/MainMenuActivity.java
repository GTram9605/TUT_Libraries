package za.ac.nplinnovations.tutlibraries.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import za.ac.nplinnovations.tutlibraries.R;
import za.ac.nplinnovations.tutlibraries.menu.books.BooksActivity;
import za.ac.nplinnovations.tutlibraries.menu.lease.RenewLeaseActivity;

public class MainMenuActivity extends AppCompatActivity {
    public final static String OVEDRIVEURL = "overdrive_url";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void onClickAudiobook(View view) {
        startActivity(new Intent(view.getContext(), OverDriveActivity.class)
        .putExtra(OVEDRIVEURL,"https://cojlibrary.overdrive.com/search?mediaType=audiobook&sortBy=newlyadded"));
    }

    public void onClickEBook(View view) {
        startActivity(new Intent(view.getContext(), OverDriveActivity.class)
                .putExtra(OVEDRIVEURL,"https://cojlibrary.overdrive.com/search?mediaType=ebook&sortBy=newlyadded"));
    }

    public void onClickRenewLease(View view) {
        startActivity(new Intent(view.getContext(), RenewLeaseActivity.class));
    }

    public void onClickTalkToLibrarian(View view) {
        startActivity(new Intent(view.getContext(), LibrarianActivity.class));
    }

    public void onClickPayfines(View view) {
        startActivity(new Intent(view.getContext(), PayFinesActivity.class));
    }

    public void onClickPaperbooks(View view) {
        startActivity(new Intent(view.getContext(), BooksActivity.class));
    }
}