package com.mindbodyonline.ironhide.Infrastructure.MindbodyViews;

import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;

import com.mindbodyonline.ironhide.PageObjects.PageObject;

import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onData;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * Extends MindbodyView
 * Complex element that allows to interact with a ListView that uses an adapter
 * Gives access to individual ListItems inside a ListView
 * Only use this element when dealing with a ListView View that has an adapter
 *
 * @param <T> The model the current element will return when interacted with
 */
public class ListAdapter<T extends PageObject> {

    private Class<T> type;
    private DataInteraction adapter;

    // itemType is the class of the objects that make up the list items in the list view
    public ListAdapter(Class<T> type, Class itemType) {
        this.type = type;
        adapter = onData(is(instanceOf(itemType)));
    }

    public ListAdapter(Class<T> type, Class itemType, int id) {
        this.type = type;
        adapter = onData(is(instanceOf(itemType))).inAdapterView(ViewMatchers.withId(id));
    }

    public ListAdapter(Class<T> type, Class itemType, Matcher<View> selector) {
        this.type = type;
        adapter = onData(is(instanceOf(itemType))).inAdapterView(selector);
    }

    private ListAdapter(Class<T> type, DataInteraction adapter) {
        this.type = type;
        this.adapter = adapter;
    }

    public <E extends PageObject> ListAdapter<E> goesTo(Class<E> type) {
        return new ListAdapter<E>(type, adapter);
    }

    public ListItem<T> getItemAtPosition(int index) {
        try {
            return new ListItem<T>(type, adapter.atPosition(index));
        } catch (Exception e) {
            return null;
        }
    }

    public ListItem<T> getFirst() {
        return new ListItem<T>(type, adapter.atPosition(0));
    }
}