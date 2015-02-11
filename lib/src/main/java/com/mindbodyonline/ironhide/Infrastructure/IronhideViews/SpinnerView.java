package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.support.test.espresso.Root;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;

import com.mindbodyonline.ironhide.Infrastructure.Extensions.SpinnerTextMatchers;
import com.mindbodyonline.ironhide.PageObjects.PageObject;

import org.hamcrest.Matcher;

/**
 * A reference view for Spinners.
 * Use this when interacting directly with {@link android.widget.Spinner}s
 *
 * @param <T> The model the current element will return when interacted with
 */
public class SpinnerView<T extends PageObject> extends MindbodyView<T> {

    /** @see MindbodyView#MindbodyView(Class, org.hamcrest.Matcher) */
    public SpinnerView(Class<T> type, Matcher<View> viewMatcher) {
        super(type, viewMatcher);
    }

    /** @see MindbodyView#MindbodyView(int) */
    public SpinnerView(int resourceId) {
        super(resourceId);
    }

    /** @see MindbodyView#MindbodyView(org.hamcrest.Matcher) */
    public SpinnerView(Matcher<View> selector) {
        super(selector);
    }

    /** @see MindbodyView#MindbodyView(int, int) */
    public SpinnerView(int IGNORED, int stringResourceId) {
        super(IGNORED, stringResourceId);
    }

    /** @see MindbodyView#MindbodyView(String) */
    public SpinnerView(String displayText) {
        super(displayText);
    }

    /**
     * Matchers
     */

    /** {@inheritDoc} */
    @Override
    public T withText(int stringId) {
        return checkMatches(ViewMatchers.withSpinnerText(stringId));
    }

    /** {@inheritDoc} */
    @Override
    public T withText(String string) {
        return checkMatches(ViewMatchers.withSpinnerText(string));
    }

    /** {@inheritDoc} */
    @Override
    public T withText(Matcher<String> stringMatcher) {
        return checkMatches(ViewMatchers.withSpinnerText(stringMatcher));
    }

    /**
     * Checks to see if the element contains the string with the given resourceId.
     * @param resourceId Resource ID of the string to check for.
     * @return The model reached by interacting with this element.
     */
    public T containsString(int resourceId) {
        return checkMatches(SpinnerTextMatchers.containsString(resourceId));
    }

    /**
     * Checks to see if a TextView's text ends with a certain string given the string's resource id.
     *
     * @param resourceId    The string's resource id
     * @return  The model reached by interacting with this element.
     */
    public T endsWith(int resourceId) {
        return checkMatches(SpinnerTextMatchers.endsWith(resourceId));
    }

    /**
     * Checks to see if a TextView's text is equal to (ignoring case) a certain string given the string's resource id.
     *
     * @param resourceId    The string's resource id
     * @return  The model reached by interacting with this element.
     */
    public T equalToIgnoringCase(int resourceId) {
        return checkMatches(SpinnerTextMatchers.equalToIgnoringCase(resourceId));
    }

    /**
     * Checks to see if a TextView's text is equal to (ignoring white space around words) a certain string given the string's resource id.
     *
     * @param resourceId    The string's resource id
     * @return   The model reached by interacting with this element.
     */
    public T equalToIgnoringWhiteSpace(int resourceId) {
        return checkMatches(SpinnerTextMatchers.equalToIgnoringWhiteSpace(resourceId));
    }

    /**
     * Checks to see if a TextView's text starts with a certain string given the string's resource id.
     *
     * @param resourceId    The string's resource id
     * @return   The model reached by interacting with this element.
     */
    public T startsWith(final int resourceId) {
        return checkMatches(SpinnerTextMatchers.startsWith(resourceId));
    }


    /** {@inheritDoc} */
    @Override
    public <E extends PageObject> SpinnerView<E> goesTo(Class<E> type) {
        return new SpinnerView<>(type, getSelector());
    }

    /**
     * Root Matchers return SpinnerView
     */

    /** {@inheritDoc} */
    @Override
    public SpinnerView<T> changeRoot() {
        return (SpinnerView<T>) super.changeRoot();
    }

    /** {@inheritDoc} */
    @Override
    public SpinnerView<T> inRoot(Matcher<Root> rootMatcher) {
        return (SpinnerView<T>) super.inRoot(rootMatcher);
    }

    /** {@inheritDoc} */
    @Override
    public SpinnerView<T> inDialogRoot() {
        return (SpinnerView<T>) super.inDialogRoot();
    }

    /** {@inheritDoc} */
    @Override
    public SpinnerView<T> inPlatformPopup() {
        return (SpinnerView<T>) super.inPlatformPopup();
    }

    /** {@inheritDoc} */
    @Override
    public SpinnerView<T> inTouchableRoot() {
        return (SpinnerView<T>) super.inTouchableRoot();
    }

    /** {@inheritDoc} */
    @Override
    public SpinnerView<T> inDecorView(Matcher<View> decorViewMatcher) {
        return (SpinnerView<T>) super.inDecorView(decorViewMatcher);
    }

    /** {@inheritDoc} */
    @Override
    public SpinnerView<T> inFocusableRoot() {
        return (SpinnerView<T>) super.inFocusableRoot();
    }
}
