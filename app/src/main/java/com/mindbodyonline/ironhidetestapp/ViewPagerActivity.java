package com.mindbodyonline.ironhidetestapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

/**
 * Activity to demonstrate actions on a {@link ViewPager}.
 */
public class ViewPagerActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pager_activity);

        final ViewPager pager = (ViewPager) findViewById(R.id.pager_layout);
        pager.setAdapter(new SimplePagerAdapter());
    }

}

