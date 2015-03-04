package com.mindbodyonline.ironhidetestapp.models;

import com.mindbodyonline.ironhide.Infrastructure.MindbodyViews.Clickable;
import com.mindbodyonline.ironhide.PageObjects.PageObject;

/**
 * A Page Object for {@link com.mindbodyonline.ironhidetestapp.MainActivity}
 */
public class MainModel extends PageObject {
    
    public Clickable<MainModel> DOES_NOT_EXIST = new Clickable<>(MainModel.class, "does not exist");
}
