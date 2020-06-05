package com.moringa.homeservice.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringa.homeservice.R;
import com.moringa.homeservice.models.Item;
import com.moringa.homeservice.ui.ResultDetailActivity;
import com.moringa.homeservice.ui.ResultDetailFragment;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultsListAdapter extends RecyclerView.Adapter<ResultsListAdapter.ResultsViewHolder> {
    private List<Item> mResults;
    private Context mContext;

    public ResultsListAdapter(List<Item> results, Context context) {
        this.mResults = results;
        this.mContext = context;
    }

    @Override
    public ResultsViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.results_list_item,parent,false);
        ResultsViewHolder viewHolder = new ResultsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( ResultsViewHolder holder, int position) {
        holder.BindResults(mResults.get(position));
    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }

    public class ResultsViewHolder  extends  RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.result_Id) TextView mTitle;
        @BindView(R.id.linkTextView)TextView mLink;
        private Context mContext;

        public ResultsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }
        public void BindResults(Item mResult){
            mTitle.setText(mResult.getTitle());
            mLink.setText(mResult.getLink());
        }

        @Override
        public void onClick(View v) {
        int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, ResultDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("results", Parcels.wrap(mResults));
            mContext.startActivity(intent);
        }
    }
}
