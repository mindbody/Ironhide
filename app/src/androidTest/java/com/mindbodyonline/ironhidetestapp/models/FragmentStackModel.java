package com.mindbodyonline.ironhidetestapp.models;

import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.Clickable;
import com.mindbodyonline.ironhide.PageObjects.PageObject;
import com.mindbodyonline.ironhidetestapp.R;

/**
 * Created by gregory.sawers on 2/24/2015.
 */
public class FragmentStackModel extends PageObject {

    public Clickable<FragmentStackModel> createNewFragment = new Clickable<FragmentStackModel>(FragmentStackModel.class, R.id.new_fragment);

    //Text field?

}
