package android.micgon.com.bbcsports.remote;

import android.micgon.com.bbcsports.BuildConfig;

/**
 * This class will have the base URL as a static variable
 * and also provide the BBCService interface to our application
 * through the getBBCService() static method.
 */

public class ApiUtils {
    public static BBCService getBBCService() {
        return RetrofitClient.getClient(BuildConfig.BASE_URL).create(BBCService.class);
    }
}
