package com.mindbodyonline.ironhidetestapp.models;

import android.support.test.espresso.action.Swipe;

import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.Clickable;
import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.Swipeable;
import com.mindbodyonline.ironhide.PageObjects.PageObject;
import com.mindbodyonline.ironhidetestapp.R;

/**
 * Created by gregory.sawers on 2/24/2015.
 */
public class SwipeActivity extends PageObject {

    public Swipeable<SwipeActivity> smallPager = new Swipeable<SwipeActivity>(SwipeActivity.class, R.id.small_pager);

    public Swipeable<SwipeActivity> largePager = new Swipeable<SwipeActivity>(SwipeActivity.class, R.id.overlapped_pager);

    public Clickable<SwipeActivity> simpleText = new Clickable<SwipeActivity>(SwipeActivity.class, R.id.text_simple);
}
