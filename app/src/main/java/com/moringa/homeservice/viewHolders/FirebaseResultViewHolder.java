package com.moringa.homeservice.viewHolders;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringa.homeservice.Constants;
import com.moringa.homeservice.R;
import com.moringa.homeservice.models.Item;
import com.moringa.homeservice.ui.ResultDetailActivity;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseResultViewHolder extends RecyclerView.ViewHolder {
    View mView;
    Context mContext;

    public FirebaseResultViewHolder( View itemView) {
        super(itemView);
        this.mView = itemView;
        this.mContext = itemView.getContext();
    }
    public void bindResult(Item mResult){
        TextView mTitle = (TextView)mView.findViewById(R.id.result_Id);
        TextView mLink = (TextView)mView.findViewById(R.id.linkTextView);

        mTitle.setText(mResult.getTitle());
        mLink.setText(mResult.getLink());
    }

}
