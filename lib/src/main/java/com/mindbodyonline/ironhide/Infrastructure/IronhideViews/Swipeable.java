package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.support.test.espresso.action.ViewActions;
import android.view.View;

import com.mindbodyonline.ironhide.Infrastructure.Extensions.BaseViewActions;

import org.hamcrest.Matcher;

/**
 * Extends BaseView
 * Simple element that allows to perform a swipe on the screen
 * Implements swipeLeft and swipeRight methods
 * Use this when the main purpose of the element will be to swipe the screen
 *
 * @param <T> The model the current element will return when interacted with
 */
public class Swipeable<T> extends BaseView<T> {

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
        return performAction(BaseViewActions.swipeDownFast());
    }

    public T swipeDownSlow(){
        return performAction(BaseViewActions.swipeDownSlow());
    }

    public T swipeDownSlow(int numTimes){
        for(int i = 0; i < numTimes-1 ; i++){
            performAction(BaseViewActions.swipeDownSlow());
        }
        return performAction(BaseViewActions.swipeDownSlow());
    }

    public T swipeUpFast(){ return performAction(BaseViewActions.swipeUpFast()); }

    public T swipeUpSlow(){ return performAction(BaseViewActions.swipeUpSlow()); }

    public T swipeUpSlow(int numTimes){
        for(int i = 0; i < numTimes-1 ; i++){
            performAction(BaseViewActions.swipeUpSlow());
        }
        return performAction(BaseViewActions.swipeUpSlow());

    }

    public T swipeFullRight(){ return performAction(BaseViewActions.swipeFullRight()); }

    public T swipeFullRight(int numTimes){
        for(int i = 0; i < numTimes-1 ; i++){
            performAction(BaseViewActions.swipeFullRight());
        }
        return performAction(BaseViewActions.swipeFullRight());

    }

    public T swipeFullLeft(){ return performAction(BaseViewActions.swipeFullLeft()); }

    public T swipeFullLeft(int numTimes){
        for(int i = 0; i < numTimes-1 ; i++){
            performAction(BaseViewActions.swipeFullLeft());
        }
        return performAction(BaseViewActions.swipeFullLeft());

    }
}
