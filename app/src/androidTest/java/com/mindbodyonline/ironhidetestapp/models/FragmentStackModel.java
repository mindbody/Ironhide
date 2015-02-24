package com.mindbodyonline.ironhidetestapp.models;

import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.Clickable;
import com.mindbodyonline.ironhide.PageObjects.PageObject;
import com.mindbodyonline.ironhidetestapp.R;

/**
 * Page Object For {@link com.mindbodyonline.ironhidetestapp.FragmentStack}
 */
public class FragmentStackModel extends PageObject {

    public Clickable<FragmentStackModel> createNewFragment = new Clickable<FragmentStackModel>(FragmentStackModel.class, R.id.new_fragment);

    //Text field?

}
