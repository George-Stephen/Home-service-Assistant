package com.moringa.homeservice.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringa.homeservice.Constants;
import com.moringa.homeservice.R;
import com.moringa.homeservice.models.Item;
import com.moringa.homeservice.viewHolders.FirebaseResultViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedResultsListActivity extends AppCompatActivity {
    private DatabaseReference mResultReference;
    private FirebaseRecyclerAdapter<Item, FirebaseResultViewHolder>mFireBaseAdapter;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);

        mResultReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_WEBSITES);
        setUpFirebaseAdapter();
    }
    private void setUpFirebaseAdapter(){
        FirebaseRecyclerOptions<Item>options= new FirebaseRecyclerOptions.Builder<Item>()
                .setQuery(mResultReference,Item.class).build();
        mFireBaseAdapter = new FirebaseRecyclerAdapter<Item, FirebaseResultViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseResultViewHolder firebaseResultViewHolder, int i, @NonNull Item item) {
                firebaseResultViewHolder.bindResult(item);
            }

            @Override
            public FirebaseResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.results_list_item,parent,false);
                return new FirebaseResultViewHolder(view);
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFireBaseAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFireBaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mFireBaseAdapter != null){
            mFireBaseAdapter.stopListening();
        }
    }
}