package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.Root;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;

import com.mindbodyonline.ironhide.PageObjects.PageObject;

import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onData;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * Complex element that allows to interact with an {@link android.widget.AdapterView} that uses an {@link android.widget.Adapter}.
 * Gives access to individual {@link com.mindbodyonline.ironhide.Infrastructure.IronhideViews.ListItem}s inside an {@link android.widget.AdapterView}.
 * Only use this element when dealing with an {@link android.widget.AdapterView} that has an {@link android.widget.Adapter}
 *
 * @param <T> The model the current element will return when interacted with
 */
public class ListAdapter<T extends PageObject> extends BaseView<T> {

    private DataInteraction adapter;

    /**
     * Retains type and adapter for use later on.
     * @param type  the class of the generic type
     * @param adapter   the {@link android.support.test.espresso.DataInteraction} for the {@link android.widget.AdapterView} this represents
     */
    @SuppressWarnings("unchecked")
    private ListAdapter(Class<T> type, DataInteraction adapter) {
        super(type, (Matcher) anything());
        this.adapter = adapter;
    }

    /**
     * A generically typed ListAdapter.
     * Instantiates a {@link android.support.test.espresso.DataInteraction}
     * @param adapterGeneric the class of the objects that make up the list items in the list view.
     */
    @SuppressWarnings("unchecked")
    public ListAdapter(Class<T> type, Class adapterGeneric) {
        super(type, (Matcher) anything());
        adapter = onData(is(instanceOf(adapterGeneric)));
    }

    /**
     * A generically typed ListAdapter.
     * Instantiates a {@link android.support.test.espresso.DataInteraction}
     * @param adapterGeneric the class of the objects that make up the list items in the list view.
     * @param id    the id of the {@link android.widget.AdapterView}
     */
    @SuppressWarnings("unchecked")
    public ListAdapter(Class<T> type, Class adapterGeneric, int id) {
        this(type, adapterGeneric, ViewMatchers.withId(id));
    }

    /**
     * A generically typed ListAdapter.
     * Instantiates a {@link android.support.test.espresso.DataInteraction}
     * @param adapterGeneric the class of the objects that make up the list items in the list view.
     * @param selector  the {@link org.hamcrest.Matcher} for the {@link android.widget.AdapterView}
     */
    public ListAdapter(Class<T> type, Class adapterGeneric, Matcher<View> selector) {
        super(type, selector);
        adapter = onData(is(instanceOf(adapterGeneric))).inAdapterView(selector);
    }

    /**
     * A generically typed ListAdapter.
     * Instantiates a {@link android.support.test.espresso.DataInteraction}
     * @param filter the {@link org.hamcrest.Matcher} for the {@link android.database.Cursor} held by the {@link android.widget.CursorAdapter}
     */
    @SuppressWarnings("unchecked")
    public ListAdapter(Class<T> type, Matcher<Object> filter) {
        super(type, (Matcher) anything());
        adapter = onData(filter);
    }

    /**
     * A generically typed ListAdapter.
     * Instantiates a {@link android.support.test.espresso.DataInteraction}
     * @param filter the {@link org.hamcrest.Matcher} for the {@link android.database.Cursor} held by the {@link android.widget.CursorAdapter}
     * @param selector  the {@link org.hamcrest.Matcher} for the {@link android.widget.AdapterView}
     */
    public ListAdapter(Class<T> type, Matcher<Object> filter, Matcher<View> selector) {
        super(type, selector);
        adapter = onData(filter).inAdapterView(selector);
    }

    /**
     * Changes which {@link android.widget.AdapterView} to look in for the data.
     * @param toMatch  the {@link BaseView} with the {@link org.hamcrest.Matcher} for the {@link android.widget.AdapterView}
     * @return  this
     */
    public ListAdapter<T> inAdapterView(BaseView<?> toMatch) {
        this.adapter = adapter.inAdapterView(toMatch.getSelector());
        return this;
    }

    /**
     * Changes the destination class by returning an object of the given type
     * @param type New class for Adapter to return to.
     * @return New ListAdapter with return type of type.
     */
    public <E extends PageObject> ListAdapter<E> goesTo(Class<E> type) {
        return new ListAdapter<>(type, adapter);
    }

    /**
     * Returns the ListItem at the given position in the List Hierarchy.
     * @param index Index of the ListItem to return.
     * @return The ListItem at position index.
     */
    public ListItem<T> getItemAtPosition(int index) {
        return new ListItem<>(type, adapter.atPosition(index)).goesTo(type);
    }

    /**
     * Returns the ListItem at the first position in the List Hierarchy.
     * @return The ListItem at the first position.
     */
    public ListItem<T> getFirst() {
        return new ListItem<>(type, adapter.atPosition(0)).goesTo(type);
    }

    /**
     * Returns the {@link ListItem} matching the given {@link BaseView}
     * @param toMatch the {@link BaseView} with the {@link org.hamcrest.Matcher} for the {@link android.widget.AdapterView} 
     */
    public ListItem<T> getItem(BaseView<?> toMatch) {
        return new ListItem<>(type, adapter.onChildView(toMatch.getSelector()));
    }

    /**
     * Actions are performed on the DataInteraction instead of the ViewInteraction 
     */
    
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
     * Root Matchers return ListAdapter
     */

    /** {@inheritDoc} */
    @Override
    public Clickable<T> changeRoot() {
        return (Clickable<T>) super.changeRoot();
    }

    /** {@inheritDoc} */
    @Override
    public Clickable<T> inRoot(Matcher<Root> rootMatcher) {
        return (Clickable<T>) super.inRoot(rootMatcher);
    }

    /** {@inheritDoc} */
    @Override
    public Clickable<T> inDialogRoot() {
        return (Clickable<T>) super.inDialogRoot();
    }

    /** {@inheritDoc} */
    @Override
    public Clickable<T> inPlatformPopup() {
        return (Clickable<T>) super.inPlatformPopup();
    }

    /** {@inheritDoc} */
    @Override
    public Clickable<T> inTouchableRoot() {
        return (Clickable<T>) super.inTouchableRoot();
    }

    /** {@inheritDoc} */
    @Override
    public Clickable<T> inDecorView(Matcher<View> decorViewMatcher) {
        return (Clickable<T>) super.inDecorView(decorViewMatcher);
    }

    /** {@inheritDoc} */
    @Override
    public Clickable<T> inFocusableRoot() {
        return (Clickable<T>) super.inFocusableRoot();
    }
}
