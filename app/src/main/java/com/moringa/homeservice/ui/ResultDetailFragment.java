package com.moringa.homeservice.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringa.homeservice.Constants;
import com.moringa.homeservice.R;
import com.moringa.homeservice.models.Item;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultDetailFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.resultTitleTextView) TextView mTitle;
    @BindView(R.id.websiteTextView) TextView mWebsiteLabel;
    @BindView(R.id.saveWebsiteButton)Button mSaveWebsite;
    private Item mResult;

    public ResultDetailFragment() {
        // Required empty public constructor
    }

    public static ResultDetailFragment newInstance(Item result) {
        ResultDetailFragment resultDetailFragment = new ResultDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("result", Parcels.wrap(result));
        resultDetailFragment.setArguments(args);
        return resultDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       mResult = Parcels.unwrap(getArguments().getParcelable("result"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result_detail, container, false);
        ButterKnife.bind(this,view);
        mTitle.setText(mResult.getTitle());
        mWebsiteLabel.setOnClickListener(this);
        mSaveWebsite.setOnClickListener(this);
        return  view;
    }

    @Override
    public void onClick(View v) {
        if (v == mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mResult.getLink()));
            startActivity(webIntent);
        }
        if (v == mSaveWebsite){
            DatabaseReference websiteRef = FirebaseDatabase.getInstance()
                    .getReference(Constants.FIREBASE_CHILD_WEBSITES);
            websiteRef.push();
            websiteRef.setValue(mResult);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }
}