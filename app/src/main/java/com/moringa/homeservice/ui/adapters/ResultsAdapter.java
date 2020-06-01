package com.moringa.homeservice.ui.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ResultsAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mTitles;
    private String[] mLinks;

    public ResultsAdapter(Context context, int resource,String[] mTitles,String[] mLinks) {
        super(context, resource);
        this.mContext = context;
        this.mTitles = mTitles;
        this.mLinks = mLinks;
    }

    @Override
    public Object getItem(int position) {
     String title = mTitles[position];
     String link = mLinks[position];
     return String.format("%s\n %s",title,link);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }
}
