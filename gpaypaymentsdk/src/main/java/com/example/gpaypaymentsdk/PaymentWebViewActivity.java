package com.example.gpaypaymentsdk;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.UUID;

public class PaymentWebViewActivity extends AppCompatActivity {
    WebView webView = null;
    private boolean isPageLoaded = false;
    private String pendingJSCall = null;

    public static PaymentResultListener paymentResultListener;
    public static PaymentWebViewActivity instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_web_view);
        instance =this;
        Toolbar toolbar = findViewById(R.id.payment_toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material);
        toolbar.setNavigationOnClickListener(v -> finish());
        setupWebView();

        String url = getIntent().getStringExtra("url");
        if (url != null) {
            webView.loadUrl(url);
        }

        handleIncomingDeepLink(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIncomingDeepLink(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        instance = null;
    }
    private void setupWebView() {
        webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setBlockNetworkLoads(false);
        webView.addJavascriptInterface(new WebAppInterface(this), "AndroidBridge");

        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                if (error.getDescription().toString().equals("net::ERR_CACHE_MISS")) {
                    view.reload();
                }
            }
            public void onPageFinished(WebView view, String url) {

                isPageLoaded = true;
                if (pendingJSCall != null) {
                    webView.evaluateJavascript(pendingJSCall, null);
                    pendingJSCall = null;
                }
            }
        });
    }
    private void handleIncomingDeepLink(Intent intent) {

        Uri uri = intent.getData();
        Bundle extras = getIntent().getExtras();

        if (uri != null && "gpaypaymentcallback".equals(uri.getScheme()) && "callback".equals(uri.getHost())) {

            String request_id = extras.getString("request_id");
            String request_time = extras.getString("request_time");


            // 2. Call the SDK listener if available
            if (paymentResultListener != null) {
                paymentResultListener.checkPayment(UUID.fromString(request_id), request_time);
            }

            // finish();

        }
    }


    public static void closePaymentActivity() {
        if (instance != null) {
            instance.finish();
        }
    }
}
