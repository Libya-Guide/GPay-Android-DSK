package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

 public class WebAppInterface {
        Context mContext;
        String callbackUrl = "app1://callback#Intent;";
        WebAppInterface(Context context) {
            mContext = context;
        }

        @JavascriptInterface
        public void openOtherApp(String requester_username, String amount,String request_id, String request_time) {

            String uri = "intent://pay?" +
                    "amount=" + amount +
                    "&requester_username=" + requester_username +
                    "&request_id=" + request_id +
                    "&request_time=" + request_time +
                    "&callback_url=" + callbackUrl +
                    "scheme=app2;" +  // This must match App2's intent filter
                    "package=com.example.myapplication2;" +
                    "end";


            try {
                Intent intent = Intent.parseUri(uri, Intent.URI_INTENT_SCHEME);
                mContext.startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(mContext, "App2 not installed", Toast.LENGTH_SHORT).show();
            }
        }


    }

