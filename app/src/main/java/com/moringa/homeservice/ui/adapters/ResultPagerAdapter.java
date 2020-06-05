package com.moringa.homeservice.ui.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.moringa.homeservice.models.Item;
import com.moringa.homeservice.ui.ResultDetailFragment;

import java.util.List;

public class ResultPagerAdapter extends FragmentPagerAdapter {
    private List<Item>mResults;

    public ResultPagerAdapter( FragmentManager fm, int behavior, List<Item> mResults) {
        super(fm, behavior);
        this.mResults = mResults;
    }

    @Override
    public Fragment getItem(int position) {
        return ResultDetailFragment.newInstance(mResults.get(position));
    }

    @Override
    public int getCount() {
        return mResults.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mResults.get(position).getTitle();
    }
}
