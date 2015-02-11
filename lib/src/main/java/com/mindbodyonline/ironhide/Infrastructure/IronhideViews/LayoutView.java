package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.support.test.espresso.Root;
import android.support.test.espresso.assertion.LayoutAssertions;
import android.support.test.espresso.matcher.LayoutMatchers;
import android.view.View;

import com.mindbodyonline.ironhide.PageObjects.PageObject;

import org.hamcrest.Matcher;

/**
 * Defines a ViewGroup typically referenced as a layout.
 * Examples include {@link android.widget.LinearLayout}, {@link android.widget.FrameLayout}, and {@link android.widget.RelativeLayout}.
 *
 * Provides generic assertions and matchers for Layouts
 *
 * @param <T> The model the current element will return when interacted with
 */
public class LayoutView<T extends PageObject> extends MindbodyView<T> {

    /** @see MindbodyView#MindbodyView(Class, org.hamcrest.Matcher) */
    public LayoutView(Class<T> type, Matcher<View> viewMatcher) {
        super(type, viewMatcher);
    }

    /** @see MindbodyView#MindbodyView(int) */
    public LayoutView(int resourceId) {
        super(resourceId);
    }

    /** @see MindbodyView#MindbodyView(org.hamcrest.Matcher) */
    public LayoutView(Matcher<View> selector) {
        super(selector);
    }

    /** @see MindbodyView#MindbodyView(int, int) */
    public LayoutView(int IGNORED, int stringResourceId) {
        super(IGNORED, stringResourceId);
    }

    /** {@inheritDoc} */
    @Override
    protected <E extends PageObject> LayoutView<E> goesTo(Class<E> type) {
        return new LayoutView<>(type, getSelector());
    }

    /**
     * View matchers specific to layouts
     */

    /**
     * Checks to see if this view matches {@link LayoutMatchers#hasEllipsizedText()}
     * @return  The model reached by interacting with this element.
     */
    @SuppressWarnings("unchecked")
    public T hasEllipsizedText() {
        return matches(LayoutMatchers.hasEllipsizedText());
    }

    /**
     * Checks to see if this view matches {@link LayoutMatchers#hasMultilineText()}
     * @return  The model reached by interacting with this element.
     */
    @SuppressWarnings("unchecked")
    public T hasMultilineText() {
        return matches(LayoutMatchers.hasMultilineText());
    }

    /**
     * View assertions specific to layouts
     */

    /**
     * Checks to see if this view matches assertion {@link LayoutAssertions#noEllipsizedText()}
     * @return  The model reached by interacting with this element.
     */
    public T noEllipsizedText() {
        return checkAssertion(LayoutAssertions.noEllipsizedText());
    }

    /**
     * Checks to see if this view matches assertion {@link LayoutAssertions#noMultilineButtons()}
     * @return  The model reached by interacting with this element.
     */
    public T noMultilineButtons() {
        return checkAssertion(LayoutAssertions.noMultilineButtons());
    }

    /**
     * Checks to see if this view matches assertion {@link LayoutAssertions#noOverlaps(org.hamcrest.Matcher)}
     * @return  The model reached by interacting with this element.
     */
    public T noOverlaps(Matcher<View> selector) {
        return checkAssertion(LayoutAssertions.noOverlaps(selector));
    }

    /**
     * Checks to see if this view matches assertion {@link LayoutAssertions#noMultilineButtons()}
     * @return  The model reached by interacting with this element.
     */
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
