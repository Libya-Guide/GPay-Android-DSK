package com.example.gpaypaymentsdk;

public enum GpayUrl {
    TESTING_STAGE("https://gpay-staging.libyaguide.net/banking/gpay_payment_page.jsp"),
    PRODUCTION("https://gpay.ly/banking/gpay_payment_page.jsp");
    private final String url;
    GpayUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
