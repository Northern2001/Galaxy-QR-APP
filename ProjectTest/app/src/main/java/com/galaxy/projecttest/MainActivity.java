package com.galaxy.projecttest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {
    private WebView webView1;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView1 = (WebView) findViewById(R.id.webview);
        String summary = "<html><head><title>Title of the document</title></head><body><h1><a href=\"hrupin://second_activity\">LINK to second activity</a></h1><div><a href=\"https://www.youtube.com/watch?v=xkszLc8Wl8w\" target=\"_blank\">Link cho Bắc mở nh&eacute;</a></div></body></html>";
        webView1.loadData(summary, "text/html", null);
//        webView1.setWebViewClient(new MyWebViewClient(this));
    }
}