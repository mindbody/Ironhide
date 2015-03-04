package com.mindbodyonline.ironhidetestapp.models;

import com.mindbodyonline.ironhide.Infrastructure.MindbodyViews.Clickable;
import com.mindbodyonline.ironhide.PageObjects.PageObject;
import com.mindbodyonline.ironhidetestapp.R;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static org.hamcrest.Matchers.allOf;

/**
 * Page Object for {@link com.mindbodyonline.ironhidetestapp.ActionBarTestActivity}
 */
public class ActionBarModel extends PageObject {
    public Clickable<ActionBarModel> HideContextualActionBar = new Clickable<>(ActionBarModel.class, R.id.hide_contextual_action_bar);
    
    public Clickable<ActionBarModel> ShowContextualActionBar = new Clickable<>(ActionBarModel.class, R.id.show_contextual_action_bar);

    public Clickable<ActionBarModel> LockAction = new Clickable<>(ActionBarModel.class, R.id.action_lock);
    
    public Clickable<ActionBarModel> SaveAction = new Clickable<>(ActionBarModel.class, R.id.action_save);

    // may require some additional parameters for compat lib
    @SuppressWarnings("unchecked")
    public Clickable<ActionBarModel> OverflowAction = new Clickable<>(ActionBarModel.class, allOf(isDisplayed(), withContentDescription("More options")));

    public Clickable<ActionBarModel> KeyAction = new Clickable<>(ActionBarModel.class, "Key");

    public Clickable<ActionBarModel> CalendarAction = new Clickable<>(ActionBarModel.class, "Calendar");
    
    public Clickable<ActionBarModel> SearchAction = new Clickable<>(ActionBarModel.class, "Search");

    public Clickable<ActionBarModel> WorldAction = new Clickable<>(ActionBarModel.class, "World");
    
    public Clickable<ActionBarModel> TextResult = new Clickable<>(ActionBarModel.class, R.id.text_action_bar_result);

}
