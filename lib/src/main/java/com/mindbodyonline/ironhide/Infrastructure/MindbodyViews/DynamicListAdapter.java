package com.mindbodyonline.ironhide.Infrastructure.MindbodyViews;

import com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers;
import com.mindbodyonline.ironhide.Infrastructure.Extensions.MindbodyViewMatchers;

import static org.hamcrest.Matchers.allOf;

/**
 * Extends MindbodyView
 * Simple element that allows to interact with ListViews that have children added dynamically
 * Enables access to ListItems inside a dynamic ListView that does not use an adapter
 * This element is rarely used, as these types of ListViews are considered bad practice
 *
 * @param <T> The model the current element will return when interacted with
 */
public class DynamicListAdapter<T> extends MindbodyView<T> {

    private int parentId;
    private Class itemType;

    public DynamicListAdapter(Class<T> type, Class itemType) {
        this.type = type;
        this.parentId = parentId;
        this.itemType = itemType;
    }

    public Clickable<T> getItemAt(int index) {
        return new Clickable<T>(type,
                allOf(ViewMatchers.isDisplayed(),
                        MindbodyViewMatchers.instanceOf(itemType),
                        MindbodyViewMatchers.hasIndex(index)));
    }
}
