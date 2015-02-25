package com.mindbodyonline.ironhidetestapp.models;

import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.Zoomable;
import com.mindbodyonline.ironhide.PageObjects.PageObject;
import com.mindbodyonline.ironhidetestapp.R;


/**
 * Page Object For {@link com.mindbodyonline.ironhidetestapp.ZoomActivity}
 */
public class ZoomModel extends PageObject {

    public Zoomable<ZoomModel> screen = new Zoomable<>(ZoomModel.class, R.id.zoom_view);

}
