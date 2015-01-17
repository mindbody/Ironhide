package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.support.test.espresso.Root;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
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

    protected final Matcher<Root> rootMatcher = not(is(DEFAULT));

    public AlternateRootView(Class<T> type, int resourceId) {
        super(type, resourceId);
        this.viewInteraction = viewInteraction.inRoot(rootMatcher);
    }

    public AlternateRootView(Class<T> type, Matcher<View> selector) {
        super(type, selector);
        this.viewInteraction = viewInteraction.inRoot(rootMatcher);
    }

    public AlternateRootView(Class<T> type, int IGNORED, int stringResourceId) {
        super(type, IGNORED, stringResourceId);
        this.viewInteraction = viewInteraction.inRoot(rootMatcher);
    }

    public AlternateRootView(Class<T> type, String displayText) {
        super(type, displayText);
        this.viewInteraction = viewInteraction.inRoot(rootMatcher);
    }

    public AlternateRootView(Class<T> type, int resourceId, Matcher<Root> rootMatcher) {
        super(type, resourceId);
        this.viewInteraction = viewInteraction.inRoot(rootMatcher);
    }

    public AlternateRootView(Class<T> type, Matcher<View> selector, Matcher<Root> rootMatcher) {
        super(type, selector);
        this.viewInteraction = viewInteraction.inRoot(rootMatcher);
    }

    public AlternateRootView(Class<T> type, int IGNORED, int stringResourceId, Matcher<Root> rootMatcher) {
        super(type, IGNORED, stringResourceId);
        this.viewInteraction = viewInteraction.inRoot(rootMatcher);
    }

    public AlternateRootView(Class<T> type, String displayText, Matcher<Root> rootMatcher) {
        super(type, displayText);
        this.viewInteraction = viewInteraction.inRoot(rootMatcher);
    }
}
