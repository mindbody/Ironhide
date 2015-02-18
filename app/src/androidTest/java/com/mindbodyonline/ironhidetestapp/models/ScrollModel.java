package com.mindbodyonline.ironhidetestapp.models;

import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.Clickable;
import com.mindbodyonline.ironhide.PageObjects.PageObject;
import com.mindbodyonline.ironhidetestapp.R;

/**
 * Page Object for {@link com.mindbodyonline.ironhidetestapp.ScrollActivity}
 */
public class ScrollModel extends PageObject {
    
    public Clickable<ScrollModel> BottomLeft = new Clickable<>(ScrollModel.class, R.id.bottom_left);
    
}
