# 🏦 GPay Payment SDK

The **GPay Payment SDK** allows Android developers to easily launch a payment page within their app and handle the payment result via a simple callback.

---

## 📦 SDK Installation

### 1. Add JitPack to your root `build.gradle`

```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
### 2. Add the dependency to your app's build.gradle`
```groovy
dependencies {
	   implementation 'com.github.Libya-Guide:GPay-Android-DSK:0.0.1'
	}
```
### 3. Sync your project.
 You’re now ready to use the SDK in your app.

## 🚀 Usage
To trigger a payment using the SDK, create a GpayPayment instance with the required parameters and call show(). You will receive the result via the PaymentResultListener.
### ✅ Example
 ``` java
public void onPayWithGpayClicked(View view) {
    GpayPayment payWithGpay = new GpayPayment(
            "50.000", // Amount
            "0edf0e40-62ab-4b80-83d6-c9fd7ce801f1", // Request ID (UUID)
            "requester_username", // Requester username
            "1746524431038", // Request timestamp
            GpayUrl.TESTING_STAGE // Environment: GpayUrl.PRODUCTION or GpayUrl.TESTING_STAGE
    );

    payWithGpay.show(MainActivity.this, new PaymentResultListener() {
        @Override
        public void checkPayment(UUID requestId, String requestTimestamp) {
            // Perform your check-payment request here
        }
    });
}
```
### 📘 Notes 
**Choose the correct environment:**
- `GpayUrl.PRODUCTION` – for live usage  
- `GpayUrl.TESTING_STAGE` – for testing and development
