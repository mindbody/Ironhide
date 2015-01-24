package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.support.test.espresso.Root;
import android.view.View;

import com.mindbodyonline.ironhide.PageObjects.PageObject;

import org.hamcrest.Matcher;

/**
 * Extends BaseView
 * Most basic element in Connect Test Suite
 * Do not implement any action that are not available to BaseView
 * Use this for any general elements on the screen (e.g.: a background, etc.)
 *
 * @param <T> The model the current element will return when interacted with
 */
public class Clickable<T extends PageObject> extends BaseView<T> {

    public Clickable(Class<T> type, Matcher<View> viewMatcher) {
        super(type, viewMatcher);
    }

    public Clickable(int resourceId) {
        super(resourceId);
    }

    public Clickable(Matcher<View> selector) {
        super(selector);
    }

    public Clickable(int IGNORED, int stringResourceId) {
        super(IGNORED, stringResourceId);
    }

    public Clickable(String displayText) {
        super(displayText);
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

    @Override
    public <E extends PageObject> Clickable<E> goesTo(Class<E> type) {
        return new Clickable<E>(type, getSelector());
    }
}
