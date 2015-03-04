package com.mindbodyonline.ironhidetestapp.models;

import com.mindbodyonline.ironhide.Infrastructure.MindbodyViews.Clickable;
import com.mindbodyonline.ironhide.Infrastructure.MindbodyViews.Swipeable;
import com.mindbodyonline.ironhide.PageObjects.PageObject;
import com.mindbodyonline.ironhidetestapp.R;

/**
 * Page Object for {@link com.mindbodyonline.ironhidetestapp.ViewPagerActivity}
 */
public class ViewPagerModel extends PageObject {

    public Swipeable<ViewPagerModel> Pager = new Swipeable<>(ViewPagerModel.class, R.id.pager_layout);

    public Clickable<ViewPagerModel> Position0 = new Clickable<>(ViewPagerModel.class, "Position #0");
    
    public Clickable<ViewPagerModel> Position1 = new Clickable<>(ViewPagerModel.class, "Position #1");
    
    public Clickable<ViewPagerModel> Position2 = new Clickable<>(ViewPagerModel.class, "Position #2");

}
