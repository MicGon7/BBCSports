package android.micgon.com.bbcsports.remote;

import android.micgon.com.bbcsports.model.Item;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * The @GET annotation explicitly defines that GET request which will be executed once the method gets called.
 * Every method in this interface must have an HTTP annotation that provides the request method and relative URL.
 * There are five built-in annotations available: @GET, @POST, @PUT, @DELETE, and @HEAD.
 * <p>
 * In the second method definition,
 * we added a query parameter for us to filter the data from the server.
 * Retrofit has the @Query("key") annotation to use instead of hard-coding it in the endpoint.
 * The key value represents the parameter name in the URL.
 * It will be added to the URL by Retrofit.
 * For example, if we pass the value "android" as an argument to the getAnswers(String tags) method,
 * the full URL will be:
 * https://api.stackexchange.com/2.2/answers?order=desc&sort=activity&site=stackoverflow&tagged=android
 */

public interface BBCService {

    @GET("top-headlines") // end point
    Call<Item> getTopArticles(@Query("apiKey") String api_key,
                              @Query("sources") String sources);

    @GET("everything")
    Call<Item> getAllArticles(@Query("apiKey") String api_key,
                              @Query("sources") String sources);

}