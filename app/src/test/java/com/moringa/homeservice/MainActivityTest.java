package com.moringa.homeservice;

import android.widget.TextView;

import com.moringa.homeservice.ui.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import static junit.framework.TestCase.assertTrue;

@RunWith(RobolectricTestRunner.class)

public class MainActivityTest {
    public MainActivity activity;

    @Before
    public void setup(){
        activity = Robolectric.setupActivity(MainActivity.class);
    }
    @Test
    public void validateTextViewContent(){
        TextView appNameTextView =activity.findViewById(R.id.mainHeader);
        assertTrue("Home service Assistant".equals(appNameTextView.getText().toString()));
    }
}
