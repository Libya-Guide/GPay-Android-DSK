package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gpaypaymentsdk.GpayPayment;
import com.example.gpaypaymentsdk.GpayUrl;
import com.example.gpaypaymentsdk.PaymentResultListener;
import com.example.gpaypaymentsdk.PaymentWebViewActivity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onCloseButtonClick(View view) {
        finish();
    }

    public void onPayWithGpayClicked(View view) {

        GpayPayment payment = new GpayPayment(
                "50.000",
                "0edf0e40-62ab-4b80-83d6-c9fd7ce801f1",
                "esraelhadi1",
                "1746524431038",
                GpayUrl.TESTING_STAGE
        );

        payment.show(MainActivity.this, new PaymentResultListener() {
            @Override
            public void checkPayment(UUID requestId, String requestTimestamp) {
                Log.d("test1", "check payment status ! request_id : " + requestId + " request_timestamp : " + requestTimestamp);
                Toast.makeText(MainActivity.this, "Payment Success: " + requestId, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
