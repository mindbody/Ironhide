package com.mindbodyonline.ironhide.Infrastructure.MindbodyViews;

import android.support.test.espresso.assertion.LayoutAssertions;
import android.support.test.espresso.matcher.LayoutMatchers;
import android.view.View;

import org.hamcrest.Matcher;

/**
 * Extends MindbodyView
 * Defines a ViewGroup typically referenced as a layout.
 * Examples include LinearLayout, FrameLayout, and RelativeLayout.
 *
 * Provides generic assertions and matchers for Layouts
 *
 * @param <T> The model the current element will return when interacted with
 */
public class LayoutView<T> extends MindbodyView<T> {

    public LayoutView(Class<T> type, int resourceId) {
        this.type = type;
        id = resourceId;
    }

    public LayoutView(Class<T> type, Matcher<View> selector) {
        this.type = type;
        this.selector = selector;
    }

    public LayoutView(Class<T> type, int resourceId, int stringResourceId) {
        this.type = type;
        id = resourceId;
        stringId = stringResourceId;
    }

    /**
     * View matchers specific to layouts
     */

    public T hasEllipsizedText() {
        return matches(LayoutMatchers.hasEllipsizedText());
    }

    public T hasMultilineText() {
        return matches(LayoutMatchers.hasMultilineText());
    }

    /**
     * View assertions specific to layouts
     */

    public T noEllipsizedText() {
        return checkAssertion(LayoutAssertions.noEllipsizedText());
    }

    public T noMultilineButtons() {
        return checkAssertion(LayoutAssertions.noMultilineButtons());
    }

    public T noOverlaps(Matcher<View> selector) {
        return checkAssertion(LayoutAssertions.noOverlaps(selector));
    }

    public T noOverlaps() {
        return checkAssertion(LayoutAssertions.noOverlaps());
    }
}
