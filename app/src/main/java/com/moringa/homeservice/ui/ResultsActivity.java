package com.moringa.homeservice.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.moringa.homeservice.Constants;
import com.moringa.homeservice.MapsActivity;
import com.moringa.homeservice.R;
import com.moringa.homeservice.Services.GoogleApi;
import com.moringa.homeservice.Services.GoogleService;
import com.moringa.homeservice.models.GItems;
import com.moringa.homeservice.models.Item;
import com.moringa.homeservice.ui.adapters.ResultsListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultsActivity extends AppCompatActivity {
    private static final String TAG = ResultsActivity.class.getSimpleName();
        @BindView(R.id.search_result_header) TextView mSearchTextView;
        @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

        @BindView(R.id.errorTextView)TextView mErrorText;
        @BindView(R.id.progressbar) ProgressBar mProgressBar;
        @BindView(R.id.mapButton)Button mMapButton;
         private ResultsListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String searchText = intent.getStringExtra("search");
        mSearchTextView.setText("Search results relating to "+ searchText);

        GoogleApi client = GoogleService.getUser();
        Call<GItems> call = client.customSearch(Constants.SEARCH_API_KEY,Constants.SEARCH_ID,searchText,"items(title,link)");

        call.enqueue(new Callback<GItems>() {
            @Override
            public void onResponse(Call<GItems> call, Response<GItems> response) {
                hideProgressBar();
                if(response.isSuccessful()){
                    List<Item>itemList = response.body().getItems();
                    String[] titles = new String[itemList.size()];
                    String[] links = new String[itemList.size()];
                    for(int i =0;i<titles.length;i++){
                        titles[i] = itemList.get(i).getTitle();
                    }
                    for(int i =0;i<links.length;i++){
                        links[i] = itemList.get(i).getLink();
                    }
                    mAdapter = new ResultsListAdapter(itemList, ResultsActivity.this);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(ResultsActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);
                    showResults();
                }else{
                    showUnsuccessfulMessage();
                }
            }
            @Override
            public void onFailure(Call<GItems> call, Throwable t) {
                Log.e(TAG,"OnFailure: ",t);
                hideProgressBar();
                showFailureMessage();
            }
        });
        mMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultsActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }
    private void showFailureMessage(){
        mErrorText.setText("Something went wrong,Please check your internet connection and try again later");
        mErrorText.setVisibility(View.VISIBLE);
    }
    private void showResults(){
        mRecyclerView.setVisibility(View.VISIBLE);
        mSearchTextView.setVisibility(View.VISIBLE);
    }
    private void hideProgressBar(){
        mProgressBar.setVisibility(View.GONE);
    }
    private void showUnsuccessfulMessage(){
        mErrorText.setText("Sorry,Your search is unavailable");
        mErrorText.setVisibility(View.VISIBLE);
    }
}