package com.moringa.homeservice.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.moringa.homeservice.MapsActivity;
import com.moringa.homeservice.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Search extends AppCompatActivity {
    @BindView(R.id.search_bar) EditText searchBar;
    @BindView(R.id.search_button) Button  searchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchKey = searchBar.getText().toString();
                Intent intent = new Intent(Search.this, MapsActivity.class);
                intent.putExtra("search",searchKey);
                startActivity(intent);
            }
        });
    }
}
