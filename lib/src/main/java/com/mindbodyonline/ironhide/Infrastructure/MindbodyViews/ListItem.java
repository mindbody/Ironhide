package com.mindbodyonline.ironhide.Infrastructure.MindbodyViews;

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
 * Extends MindbodyView
 * Simple element that allows to interact with a single item inside a ListView with an adapter
 * Enables to interact with a ListItem and the inner Views it contains
 * This element should never be instantiated - instead get a reference using a ListAdapter element
 *
 * @param <T> The model the current element will return when interacted with
 */
public class ListItem<T extends PageObject> extends MindbodyView<T> {

    private DataInteraction adapter;

    public ListItem(Class<T> type, DataInteraction adapter) {
        super(type, null);
        this.adapter = adapter;
    }

    public ListItem(DataInteraction adapter) {
        super((Matcher<View>) null);
        this.adapter = adapter;
    }

    /** {@inheritDoc} */
    @Override
    public <E extends PageObject> ListItem<E> goesTo(Class<E> type) {
        return new ListItem<E>(type, adapter);
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

    /**
     * Root Matchers return ListItem
     */

    /** {@inheritDoc} */
    @Override
    public ListItem<T> changeRoot() {
        return (ListItem<T>) super.changeRoot();
    }

    /** {@inheritDoc} */
    @Override
    public ListItem<T> inRoot(Matcher<Root> rootMatcher) {
        return (ListItem<T>) super.inRoot(rootMatcher);
    }

    /** {@inheritDoc} */
    @Override
    public ListItem<T> inDialogRoot() {
        return (ListItem<T>) super.inDialogRoot();
    }

    /** {@inheritDoc} */
    @Override
    public ListItem<T> inPlatformPopup() {
        return (ListItem<T>) super.inPlatformPopup();
    }

    /** {@inheritDoc} */
    @Override
    public ListItem<T> inTouchableRoot() {
        return (ListItem<T>) super.inTouchableRoot();
    }

    /** {@inheritDoc} */
    @Override
    public ListItem<T> inDecorView(Matcher<View> decorViewMatcher) {
        return (ListItem<T>) super.inDecorView(decorViewMatcher);
    }

    /** {@inheritDoc} */
    @Override
    public ListItem<T> inFocusableRoot() {
        return (ListItem<T>) super.inFocusableRoot();
    }


}
