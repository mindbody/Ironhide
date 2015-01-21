package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.Root;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;

import com.mindbodyonline.ironhide.PageObjects.PageObject;

import org.hamcrest.Matcher;

/**
 * Extends BaseView
 * Simple element that allows to interact with a single item inside a ListView with an adapter
 * Enables to interact with a ListItem and the inner Views it contains
 * This element should never be instantiated - instead get a reference using a ListAdapter element
 *
 * @param <T> The model the current element will return when interacted with
 */
public class ListItem<T> extends BaseView<T> {

    public ListItem(Class<T> type, DataInteraction item) {
        this.type = type;
        this.adapter = item;
    }

    @Override
    public ListItem<T> changeRoot() {
        super.changeRoot();
        return this;
    }

    @Override
    public ListItem<T> inRoot(Matcher<Root> rootMatcher) {
        super.inRoot(rootMatcher);
        return this;
    }

    // Pass in the view to click and use its selector to find it within the list item
    public T clickChildView(BaseView<T> viewToClick) {
        adapter.onChildView(viewToClick.getSelector()).perform(ViewActions.click());
        return returnGeneric();
    }

    public T childViewIsDisplayed(BaseView<T> viewToMatch) {
        adapter.onChildView(viewToMatch.getSelector()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        return returnGeneric();
    }

    public T performOnChildView(BaseView<T> viewToMatch, ViewAction toPerform) {
        adapter.onChildView(viewToMatch.getSelector()).perform(toPerform);
        return returnGeneric();
    }

    public T checkChildView(BaseView<T> viewToMatch, Matcher<View> toCheck) {
        adapter.onChildView(viewToMatch.getSelector()).check(ViewAssertions.matches(toCheck));
        return returnGeneric();
    }

    public <E extends PageObject> E performOnChildView(BaseView<T> viewToMatch, ViewAction toPerform, Class<E> type) {
        adapter.onChildView(viewToMatch.getSelector()).perform(toPerform);
        return returnGeneric(type);
    }

    public <E extends PageObject> E checkChildView(BaseView<T> viewToMatch, Matcher<View> toCheck, Class<E> type) {
        adapter.onChildView(viewToMatch.getSelector()).check(ViewAssertions.matches(toCheck));
        return returnGeneric(type);
    }

    public <E extends PageObject> E clickChildView(BaseView<T> viewToClick, Class<E> type) {
        adapter.onChildView(viewToClick.getSelector()).perform(ViewActions.click());
        return returnGeneric(type);
    }

    public <E extends PageObject> E childViewIsDisplayed(BaseView<T> viewToMatch, Class<E> type) {
        adapter.onChildView(viewToMatch.getSelector()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        return returnGeneric(type);
    }
}
