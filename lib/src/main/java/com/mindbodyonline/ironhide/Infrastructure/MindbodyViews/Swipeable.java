package com.mindbodyonline.ironhide.Infrastructure.MindbodyViews;

import android.view.View;

import com.google.android.apps.common.testing.ui.espresso.ViewAction;
import com.google.android.apps.common.testing.ui.espresso.action.GeneralLocation;
import com.google.android.apps.common.testing.ui.espresso.action.GeneralSwipeAction;
import com.google.android.apps.common.testing.ui.espresso.action.Press;
import com.google.android.apps.common.testing.ui.espresso.action.Swipe;
import com.google.android.apps.common.testing.ui.espresso.action.ViewActions;
import com.mindbodyonline.ironhide.Infrastructure.Extensions.MindbodyViewActions;

import org.hamcrest.Matcher;

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

    public T swipeDown(){
        return performAction(MindbodyViewActions.swipeDown());
    }

    public T swipeDown(int numTimes){
        for(int i = 0; i < numTimes-1 ; i++){
            performAction(MindbodyViewActions.swipeDown());
        }
        return performAction(MindbodyViewActions.swipeDown());
    }

    public T swipeUp(){
        return performAction(MindbodyViewActions.swipeUp());
    }

    public T swipeUp(int numTimes){
        for(int i = 0; i < numTimes-1 ; i++){
            performAction(MindbodyViewActions.swipeUp());
        }
        return performAction(MindbodyViewActions.swipeUp());

    }

}