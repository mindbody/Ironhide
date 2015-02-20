package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.Root;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;

import com.mindbodyonline.ironhide.PageObjects.PageObject;

import org.hamcrest.Matcher;

import static org.hamcrest.Matchers.anything;

/**
 * Simple element that allows to interact with a single item inside a {@link android.widget.ListView} with an {@link android.widget.Adapter}.
 * Enables to interact with a ListItem and the inner Views it contains.
 * This element should never be instantiated - instead get a reference using a {@link ListAdapter}
 *
 * @param <T> The model the current element will return when interacted with
 */
public class ListItem<T extends PageObject> extends BaseView<T> {

    private final DataInteraction adapter;

    /**
     * @see BaseView#BaseView(Class, org.hamcrest.Matcher)
     * Instead instantiates a {@link DataInteraction}
     */
    @SuppressWarnings("unchecked")
    public ListItem(Class<T> type, DataInteraction adapter) {
        super(type, (Matcher) anything());
        this.adapter = adapter;
    }

    /**
     * A generically typed ListItem with adapter given.
     * @param adapter   the DataInteraction representing the {@link android.widget.AdapterView}
     */
    @SuppressWarnings("unchecked")
    public ListItem(DataInteraction adapter) {
        super((Matcher) anything());
        this.adapter = adapter;
    }

    /** {@inheritDoc} */
    @Override
    public <E extends PageObject> ListItem<E> goesTo(Class<E> type) {
        return new ListItem<>(type, adapter);
    }

    /** {@inheritDoc} */
    @Override
    protected T performAction(ViewAction viewAction) {
        adapter.perform(viewAction);
        return returnGeneric();
    }

    /** {@inheritDoc} */
    @Override
    protected T checkMatches(Matcher<? super View> viewMatcher) {
        return checkAssertion(ViewAssertions.matches(viewMatcher));
    }

    /** {@inheritDoc} */
    @Override
    protected T checkAssertion(ViewAssertion viewAssertion) {
        adapter.check(viewAssertion);
        return returnGeneric();
    }

    /** {@inheritDoc} */
    @Override
    public T closeKeyboard() {
        T resultObject = performAction(ViewActions.closeSoftKeyboard());
        pause();

        return resultObject;
    }

    /**
     * Performs {@link ListItem#click()} on a view that matches the given {@link BaseView}
     * @param viewToClick   the {@link BaseView} to get the selector in order to find it within the list item
     * @return  The model reached by interacting with this element.
     */
    public T clickChildView(BaseView<?> viewToClick) {
        adapter.onChildView(viewToClick.getSelector()).perform(ViewActions.click());
        return returnGeneric();
    }

    /**
     * Performs {@link ListItem#isDisplayed()} on a view that matches the given {@link BaseView}
     * @param viewToMatch   the {@link BaseView} to get the selector in order to find it within the list item
     * @return  The model reached by interacting with this element.
     */
    public T childViewIsDisplayed(BaseView<?> viewToMatch) {
        adapter.onChildView(viewToMatch.getSelector()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        return returnGeneric();
    }

    /**
     * Performs the {@link ViewAction} on a view that matches the given {@link BaseView}
     * @param viewToMatch   the {@link BaseView} to get the selector in order to find it within the list item
     * @return  The model reached by interacting with this element.
     */
    public T performOnChildView(BaseView<?> viewToMatch, ViewAction toPerform) {
        adapter.onChildView(viewToMatch.getSelector()).perform(toPerform);
        return returnGeneric();
    }

    /**
     * Checks to see if a child view matches the {@link org.hamcrest.Matcher}
     * @param viewToMatch   the {@link BaseView} to get the selector in order to find it within the list item
     * @param toCheck   the check for the child view
     * @return  the model reached by interacting with this element.
     */
    public T checkChildView(BaseView<?> viewToMatch, Matcher<View> toCheck) {
        adapter.onChildView(viewToMatch.getSelector()).check(ViewAssertions.matches(toCheck));
        return returnGeneric();
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
