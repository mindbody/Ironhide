package com.mindbodyonline.ironhidetestapp.models;

import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.Clickable;
import com.mindbodyonline.ironhide.PageObjects.PageObject;

/**
 * Created by kyle.lozier on 2/25/2015.
 */
public class MainModel extends PageObject {
    
    public Clickable<MainModel> DOES_NOT_EXIST = new Clickable<>(MainModel.class, "does not exist");
}
