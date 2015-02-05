package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;

import com.mindbodyonline.ironhide.PageObjects.PageObject;

import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onData;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * Complex element that allows to interact with a {@link android.widget.ListView} that uses an {@link android.widget.Adapter}.
 * Gives access to individual {@link com.mindbodyonline.ironhide.Infrastructure.IronhideViews.ListItem}s inside a {@link android.widget.ListView}.
 * Only use this element when dealing with a {@link android.widget.ListView} that has an {@link android.widget.Adapter}
 *
 * @param <T> The model the current element will return when interacted with
 */
public class ListAdapter<T extends PageObject> {

    private Class<T> type;
    private DataInteraction adapter;

    /**
     * Retains type and adapter for use later on.
     * @param type  the class of the generic type
     * @param adapter   the {@link android.support.test.espresso.DataInteraction} for the {@link android.widget.AdapterView} this represents
     */
    private ListAdapter(Class<T> type, DataInteraction adapter) {
        this.type = type;
        this.adapter = adapter;
    }

    /**
     * A generically typed ListAdapter.
     * Instantiates a {@link android.support.test.espresso.DataInteraction}
     * @param itemType the class of the objects that make up the list items in the list view.
     */
    public ListAdapter(Class itemType) {
        adapter = onData(is(instanceOf(itemType)));
    }

    /**
     * A generically typed ListAdapter.
     * Instantiates a {@link android.support.test.espresso.DataInteraction}
     * @param itemType the class of the objects that make up the list items in the list view.
     * @param id    the id of the {@link android.widget.AdapterView}
     */
    public ListAdapter(Class itemType, int id) {
        this(itemType, ViewMatchers.withId(id));
    }

    /**
     * A generically typed ListAdapter.
     * Instantiates a {@link android.support.test.espresso.DataInteraction}
     * @param itemType the class of the objects that make up the list items in the list view.
     * @param selector  the {@link org.hamcrest.Matcher} for the {@link android.widget.AdapterView}
     */
    public ListAdapter(Class itemType, Matcher<View> selector) {
        adapter = onData(is(instanceOf(itemType))).inAdapterView(selector);
    }

    /**
     * A generically typed ListAdapter.
     * Instantiates a {@link android.support.test.espresso.DataInteraction}
     * @param cursorMatcher the {@link org.hamcrest.Matcher} for the {@link android.database.Cursor} held by the {@link android.widget.CursorAdapter}
     */
    public ListAdapter(Matcher<Object> cursorMatcher) {
        adapter = onData(cursorMatcher);
    }

    /**
     * A generically typed ListAdapter.
     * Instantiates a {@link android.support.test.espresso.DataInteraction}
     * @param cursorMatcher the {@link org.hamcrest.Matcher} for the {@link android.database.Cursor} held by the {@link android.widget.CursorAdapter}
     * @param selector  the {@link org.hamcrest.Matcher} for the {@link android.widget.AdapterView}
     */
    public ListAdapter(Matcher<Object> cursorMatcher, Matcher<View> selector) {
        adapter = onData(cursorMatcher).inAdapterView(selector);
    }

    /**
     * Changes the destination class by returning an object of the given type
     * @param type New class for Adapter to return to.
     * @return New ListAdapter with return type of type.
     */
    public <E extends PageObject> ListAdapter<E> goesTo(Class<E> type) {
        return new ListAdapter<E>(type, adapter);
    }

    /**
     * Returns the ListItem at the given position in the List Hierarchy.
     * @param index Index of the ListItem to return.
     * @return The ListItem at position index.
     */
    public ListItem<T> getItemAtPosition(int index) {
        return new ListItem<T>(adapter.atPosition(index)).goesTo(type);
    }

    /**
     * Returns the ListItem at the first position in the List Hierarchy.
     * @return The ListItem at the first position.
     */
    public ListItem<T> getFirst() {
        return new ListItem<T>(adapter.atPosition(0)).goesTo(type);
    }
}
