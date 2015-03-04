package com.mindbodyonline.ironhidetestapp.models;

import com.mindbodyonline.ironhide.Infrastructure.MindbodyViews.Clickable;
import com.mindbodyonline.ironhide.Infrastructure.MindbodyViews.Swipeable;
import com.mindbodyonline.ironhide.Infrastructure.MindbodyViews.Zoomable;
import com.mindbodyonline.ironhide.PageObjects.PageObject;
import com.mindbodyonline.ironhidetestapp.R;

/**
 * Page Object For {@link com.mindbodyonline.ironhidetestapp.ZoomActivity}
 */
public class ZoomModel extends PageObject {

    public Zoomable<ZoomModel> asZoomable = new Zoomable<>(ZoomModel.class, R.id.zoom_view);

    public Swipeable<ZoomModel> asSwipeable = new Swipeable<>(ZoomModel.class, R.id.zoom_view);
    
    public Clickable<ZoomModel> Name = new Clickable<>(ZoomModel.class, R.id.gesture_title);

}
