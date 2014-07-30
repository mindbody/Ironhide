package com.mindbodyonline.ironhide.Infrastructure.MindbodyViews;

import android.view.View;

import com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers;
import com.mindbodyonline.ironhide.Infrastructure.Extensions.MindbodyViewMatchers;

import org.hamcrest.Matcher;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onData;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

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
    private Class parentClass;
    private Class itemType;

    public DynamicListAdapter(Class<T> type, Class itemType) {
        this.type = type;
        this.parentId = parentId;
        this.itemType = itemType;
    }

    public DynamicListAdapter(Class<T> type, Class itemType, int parentId){
        this.type = type;
        this.parentId = parentId;
        this.itemType = itemType;
    }

    public DynamicListAdapter(Class<T> type, Class itemType, Class parentClass){
        this.type = type;
        this.itemType = itemType;
        this.parentClass = parentClass;
    }

    public DynamicListAdapter(Class<T> type, Class itemType, int parentId, Class parentClass){
        this.type = type;
        this.parentId = parentId;
        this.itemType = itemType;
        this.parentClass = parentClass;
    }

    public DynamicListAdapter(Class<T> type, Class itemType, Matcher<View> selector) {
        this.type = type;
        adapter = onData(is(instanceOf(itemType))).inAdapterView(selector);
    }

    public Clickable<T> getItemAt(int index) {
        return new Clickable<T>(type,
                allOf(ViewMatchers.isDisplayed(),
                        MindbodyViewMatchers.instanceOf(itemType),
                        MindbodyViewMatchers.hasIndex(index)));
    }

    public Clickable<T> getItemFromParentWithId(int index){

        return new Clickable<T>(type,
                allOf(ViewMatchers.isDisplayed(),
                        MindbodyViewMatchers.instanceOf(itemType),
                        MindbodyViewMatchers.hasIndex(index)
                        ,ViewMatchers.isDescendantOfA(ViewMatchers.withId(parentId))));
    }


    public Clickable<T> getItemFromParentWithClass(int index){

        return new Clickable<T>(type,
            allOf(ViewMatchers.isDisplayed(),
                MindbodyViewMatchers.instanceOf(itemType),
                MindbodyViewMatchers.hasIndex(index)
                , ViewMatchers.isDescendantOfA( MindbodyViewMatchers.instanceOf(parentClass))));
    }

    public Clickable<T> getItemFromText(String text){

        return new Clickable<T>(type,
                allOf(ViewMatchers.isDisplayed(),
                MindbodyViewMatchers.instanceOf(itemType)
                , ViewMatchers.withText(text)));


    }
}