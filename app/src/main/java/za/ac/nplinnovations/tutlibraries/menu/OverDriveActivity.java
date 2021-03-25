package za.ac.nplinnovations.tutlibraries.menu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.net.URISyntaxException;
import java.util.Objects;

import za.ac.nplinnovations.tutlibraries.R;

public class OverDriveActivity extends AppCompatActivity {
    private String TAG = "OverDrive";
    private Intent intent;
    private WebView wvMain;
    private AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overdrive);
        this.wvMain = (WebView) findViewById(R.id.wvMain);
        this.wvMain.loadUrl(getIntent().getStringExtra(MainMenuActivity.OVEDRIVEURL));
        this.wvMain.getSettings().setAllowContentAccess(true);
        this.wvMain.getSettings().setAllowFileAccess(true);
        this.wvMain.setVerticalScrollBarEnabled(true);
        this.wvMain.setHorizontalScrollBarEnabled(true);
        this.wvMain.getSettings().setJavaScriptEnabled(true);
        this.wvMain.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        this.wvMain.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                showProgress(false);
            }

            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                showProgress(true);
                Uri url = webResourceRequest.getUrl();
                if (Objects.equals(url.getScheme(), "whatsapp")) {
                    try {
                        Intent parseUri = Intent.parseUri(webResourceRequest.getUrl().toString(), Intent.URI_INTENT_SCHEME);
                        if (parseUri.resolveActivity(OverDriveActivity.this.getPackageManager()) != null) {
                            OverDriveActivity.this.startActivity(parseUri);
                        }
                        return true;
                    } catch (URISyntaxException e) {
                        Log.e(OverDriveActivity.this.TAG, e.getMessage());
                    }
                } else if (Objects.equals(url.getScheme(), "sms")) {
                    try {
                        OverDriveActivity.this.intent = Intent.parseUri(webResourceRequest.getUrl().toString(), Intent.URI_INTENT_SCHEME);
                    } catch (URISyntaxException e2) {
                        e2.printStackTrace();
                    }
                    if (OverDriveActivity.this.intent.resolveActivity(OverDriveActivity.this.getPackageManager()) != null) {
                        OverDriveActivity mainActivity = OverDriveActivity.this;
                        mainActivity.startActivity(mainActivity.intent);
                    }
                    return true;
                } else {
                    if (Objects.equals(url.getScheme(), "tel")) {
                        OverDriveActivity.this.intent = new Intent("android.intent.action.CALL");
                        OverDriveActivity.this.intent.setData(url);
                        String str = "android.permission.CALL_PHONE";
                        if (ActivityCompat.checkSelfPermission(OverDriveActivity.this, str) != 0) {
                            ActivityCompat.requestPermissions(OverDriveActivity.this, new String[]{str}, 1);
                        } else {
                            OverDriveActivity mainActivity2 = OverDriveActivity.this;
                            mainActivity2.startActivity(mainActivity2.intent);
                        }
                    } else if (Objects.equals(url.getScheme(), "mailto") || Objects.equals(url.getScheme(), "geo")) {
                        try {
                            Intent parseUri2 = Intent.parseUri(webResourceRequest.getUrl().toString(), Intent.URI_INTENT_SCHEME);
                            if (parseUri2.resolveActivity(OverDriveActivity.this.getPackageManager()) != null) {
                                OverDriveActivity.this.startActivity(parseUri2);
                            }
                            return true;
                        } catch (URISyntaxException e3) {
                            Log.e(OverDriveActivity.this.TAG, e3.getMessage());
                        }
                    }
                    return false;
                }
                return false;
            }
        });
        this.wvMain.getSettings().setAllowFileAccessFromFileURLs(true);
        this.wvMain.getSettings().setAllowUniversalAccessFromFileURLs(true);
    }

    /* access modifiers changed from: protected */
    @Override
    public void onActivityResult(int i, int i2, @Nullable Intent intent2) {
        super.onActivityResult(i, i2, intent2);
        if (i == 1) {
            if (ActivityCompat.checkSelfPermission(this, "android.permission.CALL_PHONE") == 0) {
                startActivity(this.intent);
            } else {
                Toast.makeText(this, "Permission denied to call phone.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void showProgress(boolean status){
        if(alert == null){
            LayoutInflater inflater = this.getLayoutInflater();
            final View view = inflater.inflate(R.layout.loading_activity, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
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