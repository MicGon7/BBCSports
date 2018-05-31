package android.micgon.com.bbcsports.UI;

import android.content.Context;
import android.micgon.com.bbcsports.data.model.Article;
import android.micgon.com.bbcsports.data.model.Item;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {

    private List<Article> mArticles;
    private Context mContext;
    private PostItemListener mItemListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView titleTv;
        PostItemListener mItemListener;

        public ViewHolder(View itemView, PostItemListener postItemListener) {
            super(itemView);
            titleTv = itemView.findViewById(android.R.id.text1);

            this.mItemListener = postItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Article article = getItem(getAdapterPosition());
            this.mItemListener.onPostClick(article.getTitle());

            notifyDataSetChanged();
        }
    }

    public ArticlesAdapter(Context context, List<Article> posts, PostItemListener itemListener) {
        mArticles = posts;
        mContext = context;
        mItemListener = itemListener;
    }

    @Override
    public ArticlesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);

        ViewHolder viewHolder = new ViewHolder(postView, this.mItemListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ArticlesAdapter.ViewHolder holder, int position) {

        Article article = mArticles.get(position);
        TextView textView = holder.titleTv;
        textView.setText(article.getTitle());
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    public void updateArticles(List<Article> articles) {
        mArticles = articles;
        notifyDataSetChanged();
    }

    private Article getItem(int adapterPosition) {
        return mArticles.get(adapterPosition);
    }

    public interface PostItemListener {
        void onPostClick(String title);
    }
}