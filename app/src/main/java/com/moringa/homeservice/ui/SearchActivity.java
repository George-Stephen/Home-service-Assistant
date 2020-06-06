package com.moringa.homeservice.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringa.homeservice.Constants;
import com.moringa.homeservice.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {
    @BindView(R.id.search_bar) EditText searchBar;
    @BindView(R.id.search_button) Button  searchButton;
    @BindView(R.id.mapButton)Button mMapButton;
    @BindView(R.id.savedWebsitesButton)Button mSavedWebsitesButton;
  //  private SharedPreferences mSharedPreferences;
 //   private SharedPreferences.Editor mEditor;
  private DatabaseReference mSearchHistoryReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
    //    mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
 //       mEditor = mSharedPreferences.edit();
        mSearchHistoryReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCH_HISTORY);

        mSearchHistoryReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot SearchSnapshot : dataSnapshot.getChildren()){
                    String search = SearchSnapshot.getValue().toString();
                    Log.d("Latest search", "search: " + search);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchKey = searchBar.getText().toString();
                saveSearchToFirebase(searchKey);
//               if(!(searchKey).equals("")) {
//                    addToSharedPreferences(searchKey);
//               }
                Intent intent = new Intent(SearchActivity.this, ResultsActivity.class);
                intent.putExtra("search",searchKey);
                startActivity(intent);
            }
        });
        mMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
        mSavedWebsitesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this,SavedResultsListActivity.class);
                startActivity(intent);
            }
        });
    }
    public void saveSearchToFirebase(String searchKey){
        mSearchHistoryReference.push().setValue(searchKey);
    }
  //  private void addToSharedPreferences(String searchText){
  //      mEditor.putString(Constants.PREFERENCES_SEARCH_KEY,searchText).apply();
  //  }
}
