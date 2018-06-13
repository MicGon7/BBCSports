package android.micgon.com.bbcsports.UI;

import android.content.Context;
import android.micgon.com.bbcsports.ArticleListFragment;
import android.micgon.com.bbcsports.R;
import android.micgon.com.bbcsports.model.Article;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {

    private List<Article> mArticles;
    private Context mContext;
    private PostItemListener mItemListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTitleText;
        private ImageView mImageView;
        private TextView mAuthorText;
        PostItemListener mItemListener;

        public ViewHolder(View itemView, PostItemListener postItemListener) {
            super(itemView);
            mTitleText = itemView.findViewById(R.id.supporting_text);
            mImageView = itemView.findViewById(R.id.media_image);
            mAuthorText = itemView.findViewById(R.id.author_text);


            this.mItemListener = postItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Article article = getItem(getAdapterPosition());
            this.mItemListener.onPostClick(article.getDescription());

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

        View postView = inflater.inflate(R.layout.article_list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(postView, this.mItemListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ArticlesAdapter.ViewHolder holder, int position) {

        Article article = mArticles.get(position);
        TextView textView = holder.mTitleText;
        ImageView imageView = holder.mImageView;
        textView.setText(article.getTitle());
        TextView authorText = holder.mAuthorText;
        authorText.setText(article.getAuthor());
        Glide.with(imageView).load(article.getUrlToImage()).into(imageView);

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