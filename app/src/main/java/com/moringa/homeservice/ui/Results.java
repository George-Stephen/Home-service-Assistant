package com.moringa.homeservice.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.moringa.homeservice.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Results extends AppCompatActivity {
    private String[] results = new String[]{
            "Assess the situation","Record the nature of the situation ","Contact the nearest service provider in case of any network query",
            "Check if any external resources  are required","Purchase any additional resources at the nearest stores","Attempt to resolve the situation",
            "Reassess the situation","Record if any errors have not been fixed",
            "If the situation cannot be fixed at home,call your nearest repair assistant","If not,Go to your nearest repair man for further assistance"
    };
        @BindView(R.id.search_result_header) TextView mSearchTextView;
        @BindView(R.id.results_view) ListView mResultListView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,results);
        mResultListView.setAdapter(adapter);
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
    }
}
