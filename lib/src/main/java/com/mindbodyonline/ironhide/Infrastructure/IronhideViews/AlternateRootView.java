package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.support.test.espresso.Root;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.view.View;

import com.mindbodyonline.ironhide.PageObjects.PageObject;

import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.RootMatchers.DEFAULT;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Extends BaseView
 * Simple element that allows to access views that are not in the default root
 * defined by Espresso.RootMatchers
 * Use this when the element is not a child of the default root.
 * Has constructors that take an additional Matcher<Root> parameter for specifying, otherwise
 *  defaults to using any root that is not picked by default.
 *
 * @param <T> The model the current element will return when interacted with
 */

public class AlternateRootView<T> extends BaseView<T> {

    protected Matcher<Root> rootMatcher = not(is(DEFAULT));

    public AlternateRootView(Class<T> type, int resourceId) {
        this.type = type;
        id = resourceId;
    }

    public AlternateRootView(Class<T> type, Matcher<View> selector) {
        this.type = type;
        this.selector = selector;
    }

    public AlternateRootView(Class<T> type, int resourceId, int stringResourceId) {
        this.type = type;
        id = resourceId;
        stringId = stringResourceId;
    }

    public AlternateRootView(Class<T> type, String displayText) {
        this.type = type;
        text = displayText;
    }

    public AlternateRootView(Class<T> type, int resourceId, Matcher<Root> rootMatcher) {
        this.type = type;
        id = resourceId;
        this.rootMatcher = rootMatcher;
    }

    public AlternateRootView(Class<T> type, Matcher<View> selector, Matcher<Root> rootMatcher) {
        this.type = type;
        this.selector = selector;
        this.rootMatcher = rootMatcher;
    }

    public AlternateRootView(Class<T> type, int resourceId, int stringResourceId, Matcher<Root> rootMatcher) {
        this.type = type;
        id = resourceId;
        stringId = stringResourceId;
        this.rootMatcher = rootMatcher;
    }

    public AlternateRootView(Class<T> type, String displayText, Matcher<Root> rootMatcher) {
        this.type = type;
        text = displayText;
        this.rootMatcher = rootMatcher;
    }

    /** {@inheritDoc} */
    @Override
    protected T performAction(ViewAction viewAction) {
        onView(getSelector()).inRoot(rootMatcher).perform(viewAction);
        return returnGeneric();
    }

    /** {@inheritDoc} */
    @Override
    protected <E extends PageObject> E  performAction(Class<E> type, ViewAction viewAction) {
        onView(getSelector()).inRoot(rootMatcher).perform(viewAction);
        return returnGeneric(type);
    }

    /** {@inheritDoc} */
    @Override
    protected T checkAssertion(ViewAssertion viewAssertion) {
        onView(getSelector()).inRoot(rootMatcher).check(viewAssertion);
        return returnGeneric();
    }

    /** {@inheritDoc} */
    @Override
    protected <E extends PageObject> E checkMatches(Class<E> type, Matcher<? super View> viewMatcher) {
        onView(getSelector()).inRoot(rootMatcher).check(ViewAssertions.matches(viewMatcher));
        return returnGeneric(type);
    }
}
