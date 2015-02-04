package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.view.View;

import com.mindbodyonline.ironhide.PageObjects.PageObject;

import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.mindbodyonline.ironhide.Infrastructure.Extensions.BaseViewMatchers.hasIndex;
import static com.mindbodyonline.ironhide.Infrastructure.Extensions.BaseViewMatchers.instanceOf;
import static org.hamcrest.Matchers.allOf;

/**
 * Simple element that allows to interact with ListViews that have children added dynamically.
 * Enables access to ListItems inside a dynamic ListView that does not use an adapter.
 * This element is rarely used, as these types of ListViews are considered bad practice
 *
 * @param <T> The model the current element will return when interacted with
 */
public class DynamicListAdapter<T extends PageObject> {

    private Class<T> type;
    private Matcher<View> childMatcher;

    public DynamicListAdapter(Class itemType) {
        this(null, instanceOf(itemType));
    }

    public DynamicListAdapter(Class itemType, int parentId) {
        this(null, allOf(instanceOf(itemType),
                isDescendantOfA(withId(parentId))));
    }

    public DynamicListAdapter(Class itemType, Class parentClass) {
        this(null, allOf(instanceOf(itemType),
                         isDescendantOfA( instanceOf(parentClass) )));
    }

    public DynamicListAdapter(Class itemType, int parentId, Class parentClass) {
        this(null, allOf(instanceOf(itemType),
                         isDescendantOfA(allOf(
                                withId(parentId),
                                instanceOf(parentClass) ))));
    }

    private DynamicListAdapter(Class<T> type, Matcher<View> childMatcher) {
        this.type = type;
        this.childMatcher = allOf(isDisplayed(), childMatcher);
    }


    /** @see BaseView#goesTo(Class) */
    public <E extends PageObject> DynamicListAdapter<E> goesTo(Class<E> type) {
        // TODO: remove the redundancy of isDisplayed() inside constructor
        return new DynamicListAdapter<E>(type, childMatcher);
    }

    public Clickable<T> getItemAt(int index) {
        return getItemMatching(hasIndex(index));
    }

    public Clickable<T> getItemFromText(String text) {
        return getItemMatching(withText(text));
    }

    public Clickable<T> getItemMatching(Matcher<View> itemMatcher) {
        return new Clickable<T>(allOf(childMatcher, itemMatcher)).goesTo(type);
    }
}