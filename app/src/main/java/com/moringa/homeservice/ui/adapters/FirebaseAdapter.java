package com.moringa.homeservice.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringa.homeservice.Objects.GResults;
import com.moringa.homeservice.R;
import com.moringa.homeservice.models.Item;
import com.moringa.homeservice.ui.ResultDetailActivity;


import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FirebaseAdapter extends RecyclerView.Adapter<FirebaseAdapter.ResultsHolder> {
    List<GResults> resultsList;
    private Context mContext;

    public FirebaseAdapter(List<GResults> resultsList, Context mContext) {
        this.resultsList = resultsList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ResultsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.results_list_item,parent,false);
        ResultsHolder viewHolder = new ResultsHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ResultsHolder holder, int position) {
        holder.BindResults(resultsList.get(position));
    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }
    public class ResultsHolder  extends  RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.result_Id)
        TextView mTitle;
        @BindView(R.id.linkTextView)TextView mLink;
        private Context mContext;

        public ResultsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }
        public void BindResults(GResults mResult){
            mTitle.setText(mResult.getTitle());
            mLink.setText(mResult.getLink());
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, ResultDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("results", Parcels.wrap(resultsList));
            mContext.startActivity(intent);
        }
    }
}
