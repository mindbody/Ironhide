package com.mindbodyonline.ironhidetestapp.models;

import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.Clickable;
import com.mindbodyonline.ironhide.PageObjects.PageObject;
import com.mindbodyonline.ironhidetestapp.R;

/**
 * Page Object for {@link com.mindbodyonline.ironhidetestapp.DisplayActivity}
 */
public class DisplayModel extends PageObject {
    public Clickable<DisplayModel> displayText = new Clickable<>(DisplayModel.class, R.id.display_data);
}
