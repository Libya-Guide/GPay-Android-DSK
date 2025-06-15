package com.example.gpaypaymentsdk;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.util.Log;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class GpayPayment implements Serializable {
    private String amount;
    private String requestId;
    private String requesterUsername;
    private String baseUrl;
    private String requestTimestamp;

    public GpayPayment(String amount, String requestId, String requester_username, String requestTimestamp , GpayUrl gpayUrl) {
        this.amount = amount;
        this.requestId = requestId;
        this.requesterUsername = requester_username;
        this.requestTimestamp = requestTimestamp;
        this.baseUrl= gpayUrl.getUrl();
    }

    public void show(Activity activity, PaymentResultListener listener) {
        PaymentWebViewActivity.paymentResultListener = listener;
        try {
            String installer = activity.getPackageManager().getNameForUid(Binder.getCallingUid());
            ApplicationInfo appInfo = activity.getPackageManager().getApplicationInfo(installer, 0);
            String appName = activity.getPackageManager().getApplicationLabel(appInfo).toString();
            String fullUrl = baseUrl +
                    "?amount=" + amount +
                    "&requester_username=" + requesterUsername +
                    "&request_id=" + requestId +
                    "&request_time=" + requestTimestamp+
                    "&app_name=" + URLEncoder.encode(appName, "UTF-8")+
                    "&platform=android";

            Intent intent = new Intent(activity, PaymentWebViewActivity.class);
            intent.putExtra("url", fullUrl);
            activity.startActivity(intent);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public void closePaymentWebViewActivity() {
        PaymentWebViewActivity.closePaymentActivity();
    }

    public interface OnConfirmClickListener {
        void onConfirm();
    }

    public static OnConfirmClickListener confirmClickListener;

    public static void setOnConfirmClickListener(OnConfirmClickListener listener) {
        confirmClickListener = listener;
    }
    public String getAmount() {
        return amount;
    }
    public String getRequestId() {
        return requestId;
    }
    public String getRequester_username() {
        return requesterUsername;
    }

}
