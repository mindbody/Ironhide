package com.mindbodyonline.ironhide.Infrastructure.MindbodyViews;

import android.view.View;

import org.hamcrest.Matcher;

/**
 * Extends MindbodyView
 * Most basic element in Connect Test Suite
 * Do not implement any action that are not available to MindbodyView
 * Use this for any general elements on the screen (e.g.: a background, etc.)
 *
 * @param <T> The model the current element will return when interacted with
 */
public class Clickable<T> extends MindbodyView<T> {

    public Clickable(Class<T> type, int resourceId) {
        this.type = type;
        id = resourceId;
    }

    public Clickable(Class<T> type, Matcher<View> selector) {
        this.type = type;
        this.selector = selector;
    }

    public Clickable(Class<T> type, int resourceId, int stringResourceId) {
        this.type = type;
        id = resourceId;
        stringId = stringResourceId;
    }

    public Clickable(Class<T> type, String displayText) {
        this.type = type;
        text = displayText;
    }
}
