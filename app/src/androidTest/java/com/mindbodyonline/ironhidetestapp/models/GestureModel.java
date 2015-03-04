package com.mindbodyonline.ironhidetestapp.models;


import com.mindbodyonline.ironhide.Infrastructure.MindbodyViews.Swipeable;
import com.mindbodyonline.ironhide.PageObjects.PageObject;
import com.mindbodyonline.ironhidetestapp.R;

/**
 * PageObject for {@link com.mindbodyonline.ironhidetestapp.GestureActivity}
 */
public class GestureModel extends PageObject {

    public Swipeable<GestureModel> gestureArea = new Swipeable<>(GestureModel.class, R.id.gesture_area);
}
