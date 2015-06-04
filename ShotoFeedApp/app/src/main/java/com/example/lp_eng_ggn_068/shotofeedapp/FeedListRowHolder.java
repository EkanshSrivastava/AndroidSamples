package com.example.lp_eng_ggn_068.shotofeedapp;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class FeedListRowHolder extends RecyclerView.ViewHolder {
    protected TextView title;
    protected TextView created;
    protected TextView author;

    public FeedListRowHolder(View view) {
        super(view);
        this.title = (TextView) view.findViewById(R.id.title);
        this.created = (TextView) view.findViewById(R.id.created);
        this.author = (TextView) view.findViewById(R.id.author);

    }

}
