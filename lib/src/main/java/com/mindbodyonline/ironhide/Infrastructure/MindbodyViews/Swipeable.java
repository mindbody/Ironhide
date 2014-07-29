package com.mindbodyonline.ironhide.Infrastructure.MindbodyViews;

import android.view.View;

import com.google.android.apps.common.testing.ui.espresso.ViewAction;
import com.google.android.apps.common.testing.ui.espresso.action.GeneralLocation;
import com.google.android.apps.common.testing.ui.espresso.action.GeneralSwipeAction;
import com.google.android.apps.common.testing.ui.espresso.action.Press;
import com.google.android.apps.common.testing.ui.espresso.action.Swipe;
import com.google.android.apps.common.testing.ui.espresso.action.ViewActions;
import com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers;
import com.mindbodyonline.ironhide.Infrastructure.Extensions.MindbodyViewActions;
import com.mindbodyonline.ironhide.Infrastructure.Extensions.MindbodyViewMatchers;

import org.hamcrest.Matcher;

import static org.hamcrest.Matchers.allOf;

/**
 * Extends MindbodyView
 * Simple element that allows to perform a swipe on the screen
 * Implements swipeLeft and swipeRight methods
 * Use this when the main purpose of the element will be to swipe the screen
 *
 * @param <T> The model the current element will return when interacted with
 */
public class Swipeable<T> extends MindbodyView<T> {

    public Swipeable(Class<T> type, int resourceId) {
        this.id = resourceId;
        this.type = type;
    }

    public Swipeable(Class<T> type, Matcher<View> selector) {
        this.selector = selector;
        this.type = type;
    }

    public T swipeLeft() {
        return performAction(ViewActions.swipeLeft());
    }

    public T swipeLeft(int numTimes) {
        for(int i = 0; i < numTimes-1 ; i++){
            performAction(ViewActions.swipeLeft());
        }
        return performAction(ViewActions.swipeLeft());
    }

    public T swipeRight() {
        return performAction(ViewActions.swipeRight());
    }

    public T swipeRight(int numTimes) {
        for(int i = 0; i < numTimes-1 ; i++){
            performAction(ViewActions.swipeRight());
        }
        return performAction(ViewActions.swipeRight());
    }

    public T swipeDownFast(){
        return performAction(MindbodyViewActions.swipeDownFast());
    }

    public T swipeDownSlow(){
        return performAction(MindbodyViewActions.swipeDownSlow());
    }

    public T swipeDownSlow(int numTimes){
        for(int i = 0; i < numTimes-1 ; i++){
            performAction(MindbodyViewActions.swipeDownSlow());
        }
        return performAction(MindbodyViewActions.swipeDownSlow());
    }

    public T swipeUpFast(){ return performAction(MindbodyViewActions.swipeUpFast()); }

    public T swipeUpSlow(){ return performAction(MindbodyViewActions.swipeUpSlow()); }

    public T swipeUpSlow(int numTimes){
        for(int i = 0; i < numTimes-1 ; i++){
            performAction(MindbodyViewActions.swipeUpSlow());
        }
        return performAction(MindbodyViewActions.swipeUpSlow());

    }

    public T scrollUpToClick(MindbodyView<T> element){

        while (true) {
            try {
                element.isDisplayed();
                return element.click();

            }catch (Exception e) {
                performAction(MindbodyViewActions.swipeUpSlow());
                pause(200);
            }
        }
    }

    public T selectFromListWithText(Class type, String string){

        Clickable<T> Title = new Clickable<T>(this.type, allOf(MindbodyViewMatchers.instanceOf(type), ViewMatchers.withText(string)));

        return this.scrollUpToClick(Title);
    }
}