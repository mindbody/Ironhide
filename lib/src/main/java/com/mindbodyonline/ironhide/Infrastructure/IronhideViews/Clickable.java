package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.support.test.espresso.Root;
import android.view.View;

import com.mindbodyonline.ironhide.PageObjects.PageObject;

import org.hamcrest.Matcher;

/**
 * Most basic element in Connect Test Suite.
 * Do not implement any action that are not available to BaseView.
 * Use this for any general elements on the screen (e.g.: a background, etc.)
 *
 * @param <T> The model the current element will return when interacted with
 */
public class Clickable<T extends PageObject> extends BaseView<T> {

    /** @see BaseView#BaseView(Class, org.hamcrest.Matcher) */
    public Clickable(Class<T> type, Matcher<View> viewMatcher) {
        super(type, viewMatcher);
    }

    /** @see BaseView#BaseView(int) */
    public Clickable(int resourceId) {
        super(resourceId);
    }

    /** @see BaseView#BaseView(org.hamcrest.Matcher) */
    public Clickable(Matcher<View> selector) {
        super(selector);
    }

    /** @see BaseView#BaseView(int, int) */
    public Clickable(int IGNORED, int stringResourceId) {
        super(IGNORED, stringResourceId);
    }

    /** @see BaseView#BaseView(String) */
    public Clickable(String displayText) {
        super(displayText);
    }


    /** {@inheritDoc} */
    @Override
    public <E extends PageObject> Clickable<E> goesTo(Class<E> type) {
        return new Clickable<E>(type, getSelector());
    }

    /**
     * Root Matchers return Clickable
     */

    /** {@inheritDoc} */
    @Override
    public Clickable<T> changeRoot() {
        return (Clickable<T>) super.changeRoot();
    }

    /** {@inheritDoc} */
    @Override
    public Clickable<T> inRoot(Matcher<Root> rootMatcher) {
        return (Clickable<T>) super.inRoot(rootMatcher);
    }

    /** {@inheritDoc} */
    @Override
    public Clickable<T> inDialogRoot() {
        return (Clickable<T>) super.inDialogRoot();
    }

    /** {@inheritDoc} */
    @Override
    public Clickable<T> inPlatformPopup() {
        return (Clickable<T>) super.inPlatformPopup();
    }

    /** {@inheritDoc} */
    @Override
    public Clickable<T> inTouchableRoot() {
        return (Clickable<T>) super.inTouchableRoot();
    }

    /** {@inheritDoc} */
    @Override
    public Clickable<T> inDecorView(Matcher<View> decorViewMatcher) {
        return (Clickable<T>) super.inDecorView(decorViewMatcher);
    }

    /** {@inheritDoc} */
    @Override
    public Clickable<T> inFocusableRoot() {
        return (Clickable<T>) super.inFocusableRoot();
    }
}
