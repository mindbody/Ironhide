package com.mindbodyonline.ironhidetestapp.models;

import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.Clickable;
import com.mindbodyonline.ironhide.PageObjects.PageObject;
import com.mindbodyonline.ironhidetestapp.R;

/**
 * Page Object For {@link com.mindbodyonline.ironhidetestapp.SyncActivity}
 */
public class SyncModel extends PageObject {

    public Clickable<SyncModel> helloWorld = new Clickable<>(SyncModel.class, R.id.request_button);

    public Clickable<SyncModel> statusText = new Clickable<>(SyncModel.class, R.id.status_text);
}
