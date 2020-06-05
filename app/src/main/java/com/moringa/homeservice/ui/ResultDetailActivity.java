package com.moringa.homeservice.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.moringa.homeservice.R;
import com.moringa.homeservice.models.Item;
import com.moringa.homeservice.ui.adapters.ResultPagerAdapter;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultDetailActivity extends AppCompatActivity {
    @BindView(R.id.viewPager) ViewPager mViewPager;
    private ResultPagerAdapter adapterViewPager;
    List<Item> mResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_detail);
        ButterKnife.bind(this);
        mResults = Parcels.unwrap(getIntent().getParcelableExtra("results"));
        int startingPoint = getIntent().getIntExtra("position", 0);
        adapterViewPager = new ResultPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,mResults);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPoint);
    }
}