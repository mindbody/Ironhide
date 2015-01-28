package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.support.test.espresso.Root;
import android.support.test.espresso.action.ViewActions;
import android.view.View;

import com.mindbodyonline.ironhide.Infrastructure.Extensions.SwipeActions;
import com.mindbodyonline.ironhide.PageObjects.PageObject;

import org.hamcrest.Matcher;

/**
 * Simple element that allows to perform a swipe on the screen.
 * Allows fast, slow, and multiplicative versions of Espresso's swipe actions.
 * Use this when the main purpose of the element will be to swipe the screen
 *
 * @param <T> The model the current element will return when interacted with
 */
public class Swipeable<T extends PageObject> extends BaseView<T> {

    /** @see com.mindbodyonline.ironhide.Infrastructure.IronhideViews.BaseView#BaseView(Class, org.hamcrest.Matcher) */
    public Swipeable(Class<T> type, Matcher<View> selector) {
        super(type, selector);
    }

    /** @see com.mindbodyonline.ironhide.Infrastructure.IronhideViews.BaseView#BaseView(int) */
    public Swipeable(int resourceId) {
        super(resourceId);
    }

    /** @see com.mindbodyonline.ironhide.Infrastructure.IronhideViews.BaseView#BaseView(org.hamcrest.Matcher) */
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
        return performAction(SwipeActions.swipeDownFast());
    }

    public T swipeDownSlow(){
        return performAction(SwipeActions.swipeDownSlow());
    }

    public T swipeDownSlow(int numTimes){
        for(int i = 0; i < numTimes-1 ; i++){
            performAction(SwipeActions.swipeDownSlow());
        }
        return performAction(SwipeActions.swipeDownSlow());
    }

    public T swipeUpFast(){ return performAction(SwipeActions.swipeUpFast()); }

    public T swipeUpSlow(){ return performAction(SwipeActions.swipeUpSlow()); }

    public T swipeUpSlow(int numTimes){
        for(int i = 0; i < numTimes-1 ; i++){
            performAction(SwipeActions.swipeUpSlow());
        }
        return performAction(SwipeActions.swipeUpSlow());

    }

    public T swipeFullRight(){ return performAction(SwipeActions.swipeFullRight()); }

    public T swipeFullRight(int numTimes){
        for(int i = 0; i < numTimes-1 ; i++){
            performAction(SwipeActions.swipeFullRight());
        }
        return performAction(SwipeActions.swipeFullRight());

    }

    public T swipeFullLeft(){ return performAction(SwipeActions.swipeFullLeft()); }

    public T swipeFullLeft(int numTimes){
        for(int i = 0; i < numTimes-1 ; i++){
            performAction(SwipeActions.swipeFullLeft());
        }
        return performAction(SwipeActions.swipeFullLeft());

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
