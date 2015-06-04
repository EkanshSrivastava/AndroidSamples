package com.example.lp_eng_ggn_068.shotofeedapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class MyFeedRecyclerAdapter extends RecyclerView.Adapter<FeedListRowHolder> {

    private List<HackerNewsFeedItem> hackerNewsFeedItemList;
    private Context mContext;

    public MyFeedRecyclerAdapter(Context context, List<HackerNewsFeedItem> hackerNewsFeedItemList) {
        this.hackerNewsFeedItemList = hackerNewsFeedItemList;
        this.mContext = context;
    }

    @Override
    public FeedListRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.feed_row_item, null);
        FeedListRowHolder mh = new FeedListRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(FeedListRowHolder feedListRowHolder, int i) {
        HackerNewsFeedItem hackerNewsFeedItem = hackerNewsFeedItemList.get(i);
        feedListRowHolder.title.setText(Html.fromHtml(i + 1 + ". " + hackerNewsFeedItem.getTitle()));
        feedListRowHolder.created.setText(Html.fromHtml(hackerNewsFeedItem.getCreated()));
        feedListRowHolder.author.setText(Html.fromHtml("by " + hackerNewsFeedItem.getAuthor()));

    }

    @Override
    public int getItemCount() {
        return (null != hackerNewsFeedItemList ? hackerNewsFeedItemList.size() : 0);
    }
}
