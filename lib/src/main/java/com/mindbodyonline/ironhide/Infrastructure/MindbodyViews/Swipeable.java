package com.mindbodyonline.ironhide.Infrastructure.MindbodyViews;

import android.view.View;

import com.google.android.apps.common.testing.ui.espresso.action.ViewActions;

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

    public T swipeRight() {
        return performAction(ViewActions.swipeRight());
    }
}