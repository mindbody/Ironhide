package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.support.test.espresso.Root;
import android.support.test.espresso.action.ViewActions;
import android.view.View;

import com.mindbodyonline.ironhide.Infrastructure.Extensions.BaseViewActions;
import com.mindbodyonline.ironhide.PageObjects.PageObject;

import org.hamcrest.Matcher;

/**
 * Extends BaseView
 * Simple element that allows to perform a swipe on the screen
 * Implements swipeLeft and swipeRight methods
 * Use this when the main purpose of the element will be to swipe the screen
 *
 * @param <T> The model the current element will return when interacted with
 */
public class Swipeable<T extends PageObject> extends BaseView<T> {

    public Swipeable(Class<T> type, Matcher<View> selector) {
        super(type, selector);
    }

    public Swipeable(int resourceId) {
        super(resourceId);
    }

    public Swipeable(Matcher<View> selector) {
        super(selector);
    }

    /** {@inheritDoc} */
    @Override
    public <E extends PageObject> Swipeable<E> goesTo(Class<E> type) {
        return new Swipeable<E>(type, getSelector());
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

    /**
     * Root Matchers return Swipeable
     */

    /** {@inheritDoc} */
    @Override
    public Swipeable<T> changeRoot() {
        return (Swipeable<T>) super.changeRoot();
    }

    /** {@inheritDoc} */
    @Override
    public Swipeable<T> inRoot(Matcher<Root> rootMatcher) {
        return (Swipeable<T>) super.inRoot(rootMatcher);
    }

    /** {@inheritDoc} */
    @Override
    public Swipeable<T> inDialogRoot() {
        return (Swipeable<T>) super.inDialogRoot();
    }

    /** {@inheritDoc} */
    @Override
    public Swipeable<T> inPlatformPopup() {
        return (Swipeable<T>) super.inPlatformPopup();
    }

    /** {@inheritDoc} */
    @Override
    public Swipeable<T> inTouchableRoot() {
        return (Swipeable<T>) super.inTouchableRoot();
    }

    /** {@inheritDoc} */
    @Override
    public Swipeable<T> inDecorView(Matcher<View> decorViewMatcher) {
        return (Swipeable<T>) super.inDecorView(decorViewMatcher);
    }

    /** {@inheritDoc} */
    @Override
    public Swipeable<T> inFocusableRoot() {
        return (Swipeable<T>) super.inFocusableRoot();
    }
}
