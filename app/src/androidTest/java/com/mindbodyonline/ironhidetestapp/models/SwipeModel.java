package com.mindbodyonline.ironhidetestapp.models;

import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.Clickable;
import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.Swipeable;
import com.mindbodyonline.ironhide.PageObjects.PageObject;
import com.mindbodyonline.ironhidetestapp.R;

/**
 * /**
 * Page Object For {@link com.mindbodyonline.ironhidetestapp.SwipeActivity}
 */

public class SwipeModel extends PageObject {

    public Swipeable<SwipeModel> smallPager = new Swipeable<SwipeModel>(SwipeModel.class, R.id.small_pager);

    public Swipeable<SwipeModel> largePager = new Swipeable<SwipeModel>(SwipeModel.class, R.id.overlapped_pager);

    public Clickable<SwipeModel> simpleText = new Clickable<SwipeModel>(SwipeModel.class, R.id.text_simple);
}
