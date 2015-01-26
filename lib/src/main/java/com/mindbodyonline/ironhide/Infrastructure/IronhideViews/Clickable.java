package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.support.test.espresso.Root;
import android.support.test.espresso.matcher.RootMatchers;
import android.view.View;

import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.RootMatchers.DEFAULT;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Extends BaseView
 * Most basic element in Connect Test Suite
 * Do not implement any action that are not available to BaseView
 * Use this for any general elements on the screen (e.g.: a background, etc.)
 *
 * @param <T> The model the current element will return when interacted with
 */
public class Clickable<T> extends BaseView<T> {

    public Clickable(Class<T> type, int resourceId) {
        super(type, resourceId);
    }

    public Clickable(Class<T> type, Matcher<View> selector) {
        super(type, selector);
    }

    public Clickable(Class<T> type, int IGNORED, int stringResourceId) {
        super(type, IGNORED, stringResourceId);
    }

    public Clickable(Class<T> type, String displayText) {
        super(type, displayText);
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
