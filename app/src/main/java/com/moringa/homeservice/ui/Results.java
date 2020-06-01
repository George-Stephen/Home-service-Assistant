package com.moringa.homeservice.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.moringa.homeservice.Constants;
import com.moringa.homeservice.MapsActivity;
import com.moringa.homeservice.Objects.GResults;
import com.moringa.homeservice.R;
import com.moringa.homeservice.Services.GoogleApi;
import com.moringa.homeservice.Services.GoogleService;
import com.moringa.homeservice.models.GItems;
import com.moringa.homeservice.models.Item;
import com.moringa.homeservice.ui.adapters.ResultsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Results extends AppCompatActivity {
    private static final String TAG = Results.class.getSimpleName();
        @BindView(R.id.search_result_header) TextView mSearchTextView;
        @BindView(R.id.results_view) ListView mResultListView ;
        @BindView(R.id.errorTextView)TextView mErrorText;
        @BindView(R.id.progressbar) ProgressBar mProgressBar;
        @BindView(R.id.mapButton)Button mMapButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);
        mResultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              String result = ((TextView)view).getText().toString();
                Toast.makeText(Results.this,result,Toast.LENGTH_LONG).show();
            }
        });
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
                    ResultsAdapter adapter = new ResultsAdapter(Results.this,android.R.layout.simple_list_item_1,titles,links);
                    mResultListView.setAdapter(adapter);
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
                Intent intent = new Intent(Results.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }
    private void showFailureMessage(){
        mErrorText.setText("Something went wrong,Please check your internet connection and try again later");
        mErrorText.setVisibility(View.VISIBLE);
    }
    private void showResults(){
        mResultListView.setVisibility(View.VISIBLE);
        mSearchTextView.setVisibility(View.VISIBLE);
        mMapButton.setVisibility(View.VISIBLE);
    }
    private void hideProgressBar(){
        mProgressBar.setVisibility(View.GONE);
    }
    private void showUnsuccessfulMessage(){
        mErrorText.setText("Sorry,Your search is unavailable");
        mErrorText.setVisibility(View.VISIBLE);
    }
}
