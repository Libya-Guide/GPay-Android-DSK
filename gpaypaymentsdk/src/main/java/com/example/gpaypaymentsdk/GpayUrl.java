package com.example.gpaypaymentsdk;

public enum GpayUrl {
    TESTING_STAGE("https://gpay-staging.libyaguide.net/banking/"),
    PRODUCTION("https://gpay.ly/banking/");
    private final String url;
    GpayUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
