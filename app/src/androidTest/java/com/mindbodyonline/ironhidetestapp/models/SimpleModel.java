package com.mindbodyonline.ironhidetestapp.models;

import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.Clickable;
import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.ListAdapter;
import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.SpinnerView;
import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.TextField;
import com.mindbodyonline.ironhide.PageObjects.PageObject;
import com.mindbodyonline.ironhidetestapp.R;

/**
 * Page Object for {@link com.mindbodyonline.ironhidetestapp.SimpleActivity}
 */
public class SimpleModel extends PageObject {

    public Clickable<SimpleModel> SimpleButton = new Clickable<>(SimpleModel.class, R.id.button_simple);
    
    public Clickable<SimpleModel> SimpleText = new Clickable<>(SimpleModel.class, R.id.text_simple);
    
    public TextField<SimpleModel> SimpleSendText = new TextField<>(SimpleModel.class, R.id.sendtext_simple);
    
    public Clickable<DisplayModel> SimpleSend = new Clickable<>(DisplayModel.class, R.id.send_simple);
    
    public SpinnerView<SimpleModel> SimpleSpinner = new SpinnerView<>(SimpleModel.class, R.id.spinner_simple);
    
    public ListAdapter<SimpleModel> SpinnerList = new ListAdapter<>(SimpleModel.class, String.class);
}
