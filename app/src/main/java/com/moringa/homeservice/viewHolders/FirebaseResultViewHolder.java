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

public class FirebaseResultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View mView;
    Context mContext;

    public FirebaseResultViewHolder( View itemView) {
        super(itemView);
        this.mView = itemView;
        this.mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }
    public void bindResult(Item mResult){
        TextView mTitle = (TextView)mView.findViewById(R.id.result_Id);
        TextView mLink = (TextView)mView.findViewById(R.id.linkTextView);

        mTitle.setText(mResult.getTitle());
        mTitle.setText(mResult.getLink());
    }

    @Override
    public void onClick(View v) {
        final ArrayList<Item> results = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_WEBSITES);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    results.add(snapshot.getValue(Item.class));
                }
                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext,ResultDetailActivity.class);
                intent.putExtra("position",itemPosition +"");
                intent.putExtra("results",Parcels.wrap(results));
                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        });
    }
}
