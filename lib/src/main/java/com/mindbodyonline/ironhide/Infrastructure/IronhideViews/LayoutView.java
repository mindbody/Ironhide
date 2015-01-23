package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.support.test.espresso.Root;
import android.support.test.espresso.assertion.LayoutAssertions;
import android.support.test.espresso.matcher.LayoutMatchers;
import android.view.View;

import com.mindbodyonline.ironhide.PageObjects.PageObject;

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
public class LayoutView<T extends PageObject> extends BaseView<T> {

    private LayoutView(Class<T> type, Matcher<View> viewMatcher) {
        super(type, viewMatcher);
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

    @Override
    public LayoutView<T> changeRoot() {
        super.changeRoot();
        return this;
    }

    @Override
    public LayoutView<T> inRoot(Matcher<Root> rootMatcher) {
        super.inRoot(rootMatcher);
        return this;
    }

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
}
