# 🏦 GPay Payment SDK

The **GPay Payment SDK** allows Android developers to easily launch a payment page within their app and handle the payment result via a simple callback.
### Prerequisites
- You must have an active GPay merchant account to initiate payment requests.
- you must have access to Gpay Api by obtaining your API credentials (API key, secret Key, and  Password ) from the GPay Portal. These credentials are required to authenticate and authorize all API requests.

- A payment request  must be created.
---

## 📦 SDK Installation

### 1. Add JitPack to your root `build.gradle`
#### ✅ Groovy (Java - `build.gradle`)
```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
#### ✅ Kotlin DSL (settings.gradle.kts)
```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}
```
### 2. Add the dependency to your app's build.gradle`

#### ✅ Groovy 

```groovy
dependencies {
	   implementation 'com.github.Libya-Guide:GPay-Android-DSK:latest'
}
```
#### ✅ Kotlin DSL
```kotlin
dependencies {
    implementation("com.github.Libya-Guide:GPay-Android-DSK:latest")
}
```
### 3. Sync your project.
 You’re now ready to use the SDK in your app.

## 🚀 Usage
To trigger a payment using the SDK, create a GpayPayment instance with the required parameters and call show(). You will receive the result via the PaymentResultListener.

### ✅ Example (java)
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
### ✅ Example (kotlin)
 ``` kotlin
fun onPayWithGpayClicked(view: View) {
    val payWithGpay = GpayPayment(
        "50.000", // Amount
        "0edf0e40-62ab-4b80-83d6-c9fd7ce801f1", // Request ID (UUID)
        "requester_username", // Requester username
        "1746524431038", // Request timestamp
        GpayUrl.TESTING_STAGE // Environment: GpayUrl.PRODUCTION or GpayUrl.TESTING_STAGE
    )

    payWithGpay.show(this, object : PaymentResultListener {
        override fun checkPayment(requestId: UUID, requestTimestamp: String) {
            // Perform your check-payment request here
        }
    })
    }
   ```
### 📘 Notes 
**Choose the correct environment:**
- `GpayUrl.PRODUCTION` – for live usage  
- `GpayUrl.TESTING_STAGE` – for testing and development

#### For An Example check [https://github.com/Libya-Guide/GPay-Android-Example]
