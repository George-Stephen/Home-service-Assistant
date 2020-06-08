package com.moringa.homeservice.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringa.homeservice.Constants;
import com.moringa.homeservice.Objects.GResults;
import com.moringa.homeservice.R;
import com.moringa.homeservice.models.Item;
import com.moringa.homeservice.ui.adapters.ResultsListAdapter;
import com.moringa.homeservice.viewHolders.FirebaseResultViewHolder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedResultsListActivity extends AppCompatActivity {
    private DatabaseReference mResultReference;
    private ResultsListAdapter mFireBaseAdapter;
    private ArrayList<Item>results = new ArrayList<>();


    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.progressbar) ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);
            hideProgressBar();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setHasFixedSize(true);

        mResultReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_WEBSITES);
        mResultReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                  results.add(snapshot.getValue(Item.class));
                  mRecyclerView.setAdapter(new ResultsListAdapter(results,SavedResultsListActivity.this));
                  showResults();

              }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void hideProgressBar(){
        mProgressBar.setVisibility(View.GONE);
    }
    public void showResults(){
        mRecyclerView.setVisibility(View.VISIBLE);
    }
}