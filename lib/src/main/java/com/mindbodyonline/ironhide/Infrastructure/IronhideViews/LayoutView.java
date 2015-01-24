package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.support.test.espresso.Root;
import android.support.test.espresso.assertion.LayoutAssertions;
import android.support.test.espresso.matcher.LayoutMatchers;
import android.view.View;

import org.hamcrest.Matcher;

/**
 * Extends BaseView
 * Defines a ViewGroup typically referenced as a layout.
 * Examples include LinearLayout, FrameLayout, and RelativeLayout.
 *
 * Provides generic assertions and matchers for Layouts
 *
 * @param <T> The model the current element will return when interacted with
 */
public class LayoutView<T> extends BaseView<T> {

    public LayoutView(Class<T> type, int resourceId) {
        super(type, resourceId);
    }

    public LayoutView(Class<T> type, Matcher<View> selector) {
        super(type, selector);
    }

    public LayoutView(Class<T> type, int IGNORED, int stringResourceId) {
        super(type, IGNORED, stringResourceId);
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

    /**
     * Root Matchers return LayoutView
     */

    @Override
    public LayoutView<T> changeRoot() {
        return (LayoutView<T>) super.changeRoot();
    }

    @Override
    public LayoutView<T> inRoot(Matcher<Root> rootMatcher) {
        return (LayoutView<T>) super.inRoot(rootMatcher);
    }

    @Override
    public LayoutView<T> inDialogRoot() {
        return (LayoutView<T>) super.inDialogRoot();
    }

    @Override
    public LayoutView<T> inPlatformPopup() {
        return (LayoutView<T>) super.inPlatformPopup();
    }

    @Override
    public LayoutView<T> inTouchableRoot() {
        return (LayoutView<T>) super.inTouchableRoot();
    }

    @Override
    public LayoutView<T> inDecorView(Matcher<View> decorViewMatcher) {
        return (LayoutView<T>) super.inDecorView(decorViewMatcher);
    }

    @Override
    public LayoutView<T> inFocusableRoot() {
        return (LayoutView<T>) super.inFocusableRoot();
    }
}
