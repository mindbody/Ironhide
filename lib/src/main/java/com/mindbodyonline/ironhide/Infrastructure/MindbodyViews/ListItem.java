package com.mindbodyonline.ironhide.Infrastructure.MindbodyViews;

import android.view.View;

import com.google.android.apps.common.testing.ui.espresso.DataInteraction;
import com.google.android.apps.common.testing.ui.espresso.ViewAction;
import com.google.android.apps.common.testing.ui.espresso.action.ViewActions;
import com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions;
import com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers;

import com.mindbodyonline.ironhide.PageObjects.PageObject;
import org.hamcrest.Matcher;

/**
 * Extends MindbodyView
 * Simple element that allows to interact with a single item inside a ListView with an adapter
 * Enables to interact with a ListItem and the inner Views it contains
 * This element should never be instantiated - instead get a reference using a ListAdapter element
 *
 * @param <T> The model the current element will return when interacted with
 */
public class ListItem<T> extends MindbodyView<T> {

    public ListItem(Class<T> type, DataInteraction item) {
        this.type = type;
        this.adapter = item;
    }

    // Pass in the view to click and use its selector to find it within the list item
    public T clickChildView(MindbodyView<T> viewToClick) {
        adapter.onChildView(viewToClick.getSelector()).perform(ViewActions.click());
        return returnGeneric();
    }

    public T childViewIsDisplayed(MindbodyView<T> viewToMatch) {
        adapter.onChildView(viewToMatch.getSelector()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        return returnGeneric();
    }

    public T performOnChildView(MindbodyView<T> viewToMatch, ViewAction toPerform) {
        adapter.onChildView(viewToMatch.getSelector()).perform(toPerform);
        return returnGeneric();
    }

    public T checkChildView(MindbodyView<T> viewToMatch, Matcher<View> toCheck) {
        adapter.onChildView(viewToMatch.getSelector()).check(ViewAssertions.matches(toCheck));
        return returnGeneric();
    }

    public <E extends PageObject> E performOnChildView(MindbodyView<T> viewToMatch, ViewAction toPerform, Class<E> type) {
        adapter.onChildView(viewToMatch.getSelector()).perform(toPerform);
        return returnGeneric(type);
    }

    public <E extends PageObject> E checkChildView(MindbodyView<T> viewToMatch, Matcher<View> toCheck, Class<E> type) {
        adapter.onChildView(viewToMatch.getSelector()).check(ViewAssertions.matches(toCheck));
        return returnGeneric(type);
    }

    public <E extends PageObject> E clickChildView(MindbodyView<T> viewToClick, Class<E> type) {
        adapter.onChildView(viewToClick.getSelector()).perform(ViewActions.click());
        return returnGeneric(type);
    }

    public <E extends PageObject> E childViewIsDisplayed(MindbodyView<T> viewToMatch, Class<E> type) {
        adapter.onChildView(viewToMatch.getSelector()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        return returnGeneric(type);
    }
}
