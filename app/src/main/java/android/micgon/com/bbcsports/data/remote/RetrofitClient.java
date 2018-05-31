package android.micgon.com.bbcsports.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *  This class will create a singleton of Retrofit.
 *  Retrofit needs a base URL to build its instance,
 *  so we will pass a URL when calling RetrofitClient.getClient(String baseUrl).
 *  This URL will then be used to build the instance in line 19.
 *  We are also specifying the JSON converter we need (Gson) in line 20.
 */
public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if(retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
