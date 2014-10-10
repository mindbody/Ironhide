package com.mindbodyonline.ironhide.Infrastructure.MindbodyViews;

import android.view.View;

import com.google.android.apps.common.testing.ui.espresso.ViewAction;
import com.google.android.apps.common.testing.ui.espresso.ViewAssertion;
import com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions;
import com.mindbodyonline.ironhide.PageObjects.PageObject;

import org.hamcrest.Matcher;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.matcher.RootMatchers.DEFAULT;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Extends MindbodyView
 * Simple element that allows to access views that are not in the default root
 * defined by Espresso.RootMatchers
 * Use this when the element is not a child of the default root.
 *
 * @param <T> The model the current element will return when interacted with
 */
public class AlternateRootView<T> extends MindbodyView<T> {

    /** {@inheritDoc} */
    @Override
    protected T performAction(ViewAction viewAction) {
        onView(getSelector()).inRoot(not(is(DEFAULT))).perform(viewAction);
        return returnGeneric();
    }

    /** {@inheritDoc} */
    @Override
    protected <E extends PageObject> E  performAction(Class<E> type, ViewAction viewAction) {
        onView(getSelector()).inRoot(not(is(DEFAULT))).perform(viewAction);
        return returnGeneric(type);
    }

    /** {@inheritDoc} */
    @Override
    protected T checkAssertion(ViewAssertion viewAssertion) {
        onView(getSelector()).inRoot(not(is(DEFAULT))).check(viewAssertion);
        return returnGeneric();
    }

    /** {@inheritDoc} */
    @Override
    protected <E extends PageObject> E checkMatches(Class<E> type, Matcher<? super View> viewMatcher) {
        onView(getSelector()).inRoot(not(is(DEFAULT))).check(ViewAssertions.matches(viewMatcher));
        return returnGeneric(type);
    }
}
