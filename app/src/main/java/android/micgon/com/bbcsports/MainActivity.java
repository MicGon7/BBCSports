package android.micgon.com.bbcsports;

import android.micgon.com.bbcsports.UI.ArticlesAdapter;
import android.micgon.com.bbcsports.data.model.Article;
import android.micgon.com.bbcsports.data.model.Item;
import android.micgon.com.bbcsports.data.model.Source;
import android.micgon.com.bbcsports.data.remote.ApiUtils;
import android.micgon.com.bbcsports.data.remote.BBCService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String API_KEY = "5e46a0b9d76b40da8fe3f7dc251d5b3b";

    private RecyclerView mRecyclerView;
    private BBCService mService;
    private ArticlesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mService = ApiUtils.getBBCService();
        mRecyclerView = findViewById(R.id.rv_articles);
        mAdapter = new ArticlesAdapter(this, new ArrayList<Article>(0),
                new ArticlesAdapter.PostItemListener() {
                    @Override
                    public void onPostClick(String title) {
                        Toast.makeText(MainActivity.this,
                                "Title of article is " + title,
                                Toast.LENGTH_SHORT).show();
                    }
                });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        loadArticles();

    }

    public void loadArticles() {
        mService.getTopArticles().enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if (response.isSuccessful()) {
                    mAdapter.updateArticles(response.body().getArticles());
                    Toast.makeText(MainActivity.this, "Get Successful", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "articles loaded from API");
                } else {
                    int statusCode = response.code();
                    // handle request errors depending on status code.
                }
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Log.d(TAG, "error loading from API");
                Toast.makeText(MainActivity.this, "Get failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
