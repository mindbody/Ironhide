package com.mindbodyonline.ironhidetestapp.models;

import com.mindbodyonline.ironhide.Infrastructure.MindbodyViews.Clickable;
import com.mindbodyonline.ironhide.PageObjects.PageObject;
import com.mindbodyonline.ironhidetestapp.R;

/**
 * Page Object For {@link com.mindbodyonline.ironhidetestapp.FragmentStack}
 */
public class FragmentStackModel extends PageObject {

    public Clickable<FragmentStackModel> createNewFragment = new Clickable<>(FragmentStackModel.class, R.id.new_fragment);

    public Clickable<FragmentStackModel> fragmenText1 = new Clickable<>(FragmentStackModel.class, "Fragment #1");

    public Clickable<FragmentStackModel> fragmenText2 = new Clickable<>(FragmentStackModel.class, "Fragment #2");

}
