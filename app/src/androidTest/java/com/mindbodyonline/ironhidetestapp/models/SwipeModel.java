package com.mindbodyonline.ironhidetestapp.models;

import com.mindbodyonline.ironhide.Infrastructure.MindbodyViews.Clickable;
import com.mindbodyonline.ironhide.Infrastructure.MindbodyViews.Swipeable;
import com.mindbodyonline.ironhide.PageObjects.PageObject;
import com.mindbodyonline.ironhidetestapp.R;

/**
 * /**
 * Page Object For {@link com.mindbodyonline.ironhidetestapp.SwipeActivity}
 */

public class SwipeModel extends PageObject {

    public Swipeable<SwipeModel> smallPager = new Swipeable<>(SwipeModel.class, R.id.small_pager);

    public Swipeable<SwipeModel> largePager = new Swipeable<>(SwipeModel.class, R.id.overlapped_pager);

    public Clickable<SwipeModel> simpleText = new Clickable<>(SwipeModel.class, R.id.text_simple);

    public Clickable<SwipeModel> Position0 = new Clickable<>(SwipeModel.class, "Position #0");

    public Clickable<SwipeModel> Position1 = new Clickable<>(SwipeModel.class, "Position #1");

    public Clickable<SwipeModel> Position2 = new Clickable<>(SwipeModel.class, "Position #2");
}
