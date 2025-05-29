package com.example.gpaypaymentsdk;

import android.content.Context;
import android.content.Intent;
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
               Toast.makeText(mContext, "App2 not installed", Toast.LENGTH_SHORT).show();
           }
       }
    @JavascriptInterface
    public void onConfirmClicked(String requestId, String requestTime) {
        if (PaymentWebViewActivity.paymentResultListener != null) {
            PaymentWebViewActivity.paymentResultListener.checkPayment(UUID.fromString(requestId), requestTime);
        }

    }
    }

