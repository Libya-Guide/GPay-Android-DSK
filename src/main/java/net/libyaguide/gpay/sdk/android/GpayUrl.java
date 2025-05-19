package net.libyaguide.gpay.sdk.android;

public enum GpayUrl {
    TESTING_STAGE("http://192.168.97.152:5500"),
    PRODUCTION("http://192.168.97.152:5500");
    private final String url;
    GpayUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
