package net.libyaguide.gpay.sdk.android;

import java.util.UUID;

public interface PaymentResultListener {
    void checkPayment(UUID request_id,String request_timestamp);
}
