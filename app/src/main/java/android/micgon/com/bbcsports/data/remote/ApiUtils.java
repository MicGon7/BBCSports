package android.micgon.com.bbcsports.data.remote;

/**
 * This class will have the base URL as a static variable
 * and also provide the BBCService interface to our application
 * through the getBBCService() static method.
 */

public class ApiUtils {

    public static final String BASE_URL = "https://newsapi.org/v2/";

    public static BBCService getBBCService() {
        return RetrofitClient.getClient(BASE_URL).create(BBCService.class);
    }
}
