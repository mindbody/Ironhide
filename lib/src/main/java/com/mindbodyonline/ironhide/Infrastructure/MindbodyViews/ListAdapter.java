package com.mindbodyonline.ironhide.Infrastructure.MindbodyViews;

import com.google.android.apps.common.testing.ui.espresso.DataInteraction;
import com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onData;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
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
public class ListAdapter<T> extends MindbodyView<T> {

    DataInteraction adapter;

    // itemType is the class of the objects that make up the list items in the list view
    public ListAdapter(Class<T> type, Class itemType) {
        this.type = type;
        adapter = onData(allOf(is(instanceOf(itemType))));
    }

    public ListAdapter(Class<T> type, Class itemType, int id) {
        this.type = type;
        adapter = onData(allOf(is(instanceOf(itemType)))).inAdapterView(ViewMatchers.withId(id));
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
