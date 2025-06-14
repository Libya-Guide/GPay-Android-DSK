package com.example.gpaypaymentsdk;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

public class WebAppInterface {
       Context mContext;
       WebAppInterface(Context context) {
           mContext = context;
       }

       @JavascriptInterface
       public void openOtherApp(String requester_username, String amount,String request_id, String request_time,String app_name) throws JSONException, UnsupportedEncodingException {
           JSONObject json = new JSONObject();
           json.put("request_id", request_id);
           json.put("requester_username", requester_username);
           json.put("request_time", request_time);
           json.put("app_name", app_name);
           json.put("platform", "android");

           String js = URLEncoder.encode(json.toString(), "UTF-8");

           String uri = "intent://makepaymentto" +
                   "#Intent;" +
                   "scheme=gpay;" +
                   "package=com.libyaguide.moh_elwaer.gpay;" +
                   "S.js=" + js + ";" +
                   "end";
           try {
               Intent intent = Intent.parseUri(uri, Intent.URI_INTENT_SCHEME);
               mContext.startActivity(intent);
           } catch (Exception e) {
               Toast.makeText(mContext, "GPay App not installed", Toast.LENGTH_SHORT).show();
               try {
                   Intent playStoreIntent = new Intent(Intent.ACTION_VIEW,
                           Uri.parse("market://details?id=com.libyaguide.moh_elwaer.gpay"));
                   playStoreIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   mContext.startActivity(playStoreIntent);
               } catch (ActivityNotFoundException ex) {
                   Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                           Uri.parse("https://play.google.com/store/apps/details?id=com.libyaguide.moh_elwaer.gpay"));
                   browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   mContext.startActivity(browserIntent);
               }
           }
       }
    @JavascriptInterface
    public void onConfirmClicked(String requestId, String requestTime) {
        if (PaymentWebViewActivity.paymentResultListener != null) {
            PaymentWebViewActivity.paymentResultListener.checkPayment(UUID.fromString(requestId), requestTime);
        }

    }
    }

