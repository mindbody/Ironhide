package com.mindbodyonline.ironhide.Infrastructure.MindbodyViews;

import android.support.test.espresso.Root;
import android.support.test.espresso.assertion.LayoutAssertions;
import android.support.test.espresso.matcher.LayoutMatchers;
import android.view.View;

import com.mindbodyonline.ironhide.PageObjects.PageObject;

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
public class LayoutView<T extends PageObject> extends MindbodyView<T> {

    public LayoutView(Class<T> type, int resourceId) {
        super(resourceId);
        this.type = type;
        id = resourceId;
    }

    public LayoutView(Class<T> type, Matcher<View> selector) {
        super(type, selector);
        this.type = type;
        this.selector = selector;
    }

    public LayoutView(Class<T> type, int resourceId, int stringResourceId) {
        super(resourceId);
        this.type = type;
        id = resourceId;
        stringId = stringResourceId;
    }


    public LayoutView(int resourceId) {
        super(resourceId);
    }

    public LayoutView(Matcher<View> selector) {
        super(selector);
    }

    public LayoutView(int IGNORED, int stringResourceId) {
        super(IGNORED, stringResourceId);
    }

    /** {@inheritDoc} */
    @Override
    protected <E extends PageObject> LayoutView<E> goesTo(Class<E> type) {
        return new LayoutView<E>(type, getSelector());
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

    /** {@inheritDoc} */
    @Override
    public LayoutView<T> changeRoot() {
        return (LayoutView<T>) super.changeRoot();
    }

    /** {@inheritDoc} */
    @Override
    public LayoutView<T> inRoot(Matcher<Root> rootMatcher) {
        return (LayoutView<T>) super.inRoot(rootMatcher);
    }

    /** {@inheritDoc} */
    @Override
    public LayoutView<T> inDialogRoot() {
        return (LayoutView<T>) super.inDialogRoot();
    }

    /** {@inheritDoc} */
    @Override
    public LayoutView<T> inPlatformPopup() {
        return (LayoutView<T>) super.inPlatformPopup();
    }

    /** {@inheritDoc} */
    @Override
    public LayoutView<T> inTouchableRoot() {
        return (LayoutView<T>) super.inTouchableRoot();
    }

    /** {@inheritDoc} */
    @Override
    public LayoutView<T> inDecorView(Matcher<View> decorViewMatcher) {
        return (LayoutView<T>) super.inDecorView(decorViewMatcher);
    }

    /** {@inheritDoc} */
    @Override
    public LayoutView<T> inFocusableRoot() {
        return (LayoutView<T>) super.inFocusableRoot();
    }
}
