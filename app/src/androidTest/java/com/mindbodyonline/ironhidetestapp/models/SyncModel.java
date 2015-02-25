package com.mindbodyonline.ironhidetestapp.models;

import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.Clickable;
import com.mindbodyonline.ironhide.PageObjects.PageObject;
import com.mindbodyonline.ironhidetestapp.R;

/**
 * Page Object for {@link com.mindbodyonline.ironhidetestapp.SyncActivity}
 */
public class SyncModel extends PageObject {
    
    public Clickable<SyncModel> HiddenTextView = new Clickable<>(SyncModel.class, R.id.status_text);
    
    public Clickable<SyncModel> RequestButton = new Clickable<>(SyncModel.class, R.id.request_button);
}
