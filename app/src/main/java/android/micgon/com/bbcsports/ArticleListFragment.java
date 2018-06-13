package android.micgon.com.bbcsports;


import android.micgon.com.bbcsports.UI.ArticlesAdapter;
import android.micgon.com.bbcsports.model.Article;
import android.micgon.com.bbcsports.model.Item;
import android.micgon.com.bbcsports.remote.ApiUtils;
import android.micgon.com.bbcsports.remote.BBCService;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleListFragment extends Fragment {

    private static final String TAG = "ArticleListFragment";


    private RecyclerView mRecyclerView;
    private BBCService mService;
    private ArticlesAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_article_list, container, false);

        mService = ApiUtils.getBBCService();
        mRecyclerView = v.findViewById(R.id.article_recycler_view);
        mAdapter = new ArticlesAdapter(getActivity(), new ArrayList<Article>(0),
                new ArticlesAdapter.PostItemListener() {
                    @Override
                    public void onPostClick(String title) {
                        Toast.makeText(getActivity(),
                                "Title of article is " + title,
                                Toast.LENGTH_SHORT).show();
                    }
                });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        loadArticles();

        return v;
    }

    public void loadArticles() {
        mService.getAllArticles(BuildConfig.API_KEY, "bleacher-report").enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if (response.isSuccessful()) {
                    mAdapter.updateArticles(response.body().getArticles());
                    Toast.makeText(getActivity(), "Get Successful", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "articles loaded from API");
                } else {
                    int statusCode = response.code();
                    // handle request errors depending on status code.
                }
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Log.d(TAG, "error loading from API");
                Toast.makeText(getActivity(), "Get failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
