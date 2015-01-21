package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.support.test.espresso.Root;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;

import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onData;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * Extends BaseView
 * Complex element that allows to interact with a ListView that uses an adapter
 * Gives access to individual ListItems inside a ListView
 * Only use this element when dealing with a ListView View that has an adapter
 *
 * @param <T> The model the current element will return when interacted with
 */
public class ListAdapter<T> extends BaseView<T> {

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

    @Override
    public ListAdapter<T> changeRoot() {
        super.changeRoot();
        return this;
    }

    @Override
    public ListAdapter<T> inRoot(Matcher<Root> rootMatcher) {
        super.inRoot(rootMatcher);
        return this;
    }

    public ListItem<T> getItemAtPosition(int index) {
        /**
         * Users of this would have to catch NullPointerExceptions anyways
         *  pending removal for OpenSource
         */
//        try {
            return new ListItem<T>(type, adapter.atPosition(index));
//        } catch (IndexOutOfBoundsException e) {
//            return null;
//        }
    }

    public ListItem<T> getFirst() {
        return new ListItem<T>(type, adapter.atPosition(0));
    }
}
