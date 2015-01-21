package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.support.test.espresso.Root;
import android.view.View;

import org.hamcrest.Matcher;

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

    @Override
    public Clickable<T> changeRoot() {
        super.changeRoot();
        return this;
    }

    @Override
    public Clickable<T> inRoot(Matcher<Root> rootMatcher) {
        super.inRoot(rootMatcher);
        return this;
    }
}
