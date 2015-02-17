package com.mindbodyonline.ironhidetestapp.models;

import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.Clickable;
import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.ListAdapter;
import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.NavDrawer;
import com.mindbodyonline.ironhide.PageObjects.PageObject;
import com.mindbodyonline.ironhidetestapp.R;

/**
 * Page Ojbect for {@link com.mindbodyonline.ironhidetestapp.DrawerActivity}
 */
public class DrawerModel extends PageObject {
    
    public NavDrawer<DrawerModel> Drawer = new NavDrawer<>(DrawerModel.class, R.id.drawer_layout);
    
    public ListAdapter<DrawerModel> DrawerList = new ListAdapter<>(DrawerModel.class, String.class);
    
    public Clickable<DrawerModel> DrawerText = new Clickable<>(DrawerModel.class, R.id.drawer_text_view);
}
