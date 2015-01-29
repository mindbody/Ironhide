package com.mindbodyonline.ironhide.Infrastructure.MindbodyViews;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.PickerActions;
import android.view.View;

import com.mindbodyonline.ironhide.Infrastructure.Extensions.PickDateAction;
import com.mindbodyonline.ironhide.PageObjects.PageObject;

import org.hamcrest.Matcher;

import java.util.Date;

/**
 * Extends MindbodyView
 * Simple element that allows to interact with a DatePicker View
 * Implements updateDate method, which handles the PickDateAction ViewAction
 * Only use this element when dealing with an ImageView View
 *
 * @param <T> The model the current element will return when interacted with
 */
public class DatePicker< T extends PageObject> extends MindbodyView<T> {

    public DatePicker(Class<T> type, Matcher<View> viewMatcher) {
        super(type, viewMatcher);
    }

    public DatePicker(int resourceId) {
        super(resourceId);
    }

    public DatePicker(Matcher<View> selector) {
        super(selector);
    }

    public DatePicker(int IGNORED, int stringResourceId) {
        super(IGNORED, stringResourceId);
    }
    public DatePicker(String displayText) {
        super(displayText);
    }

    public T setDate(int year, int monthOfYear, int dayOfMonth) {
        return performAction(PickerActions.setDate(year, monthOfYear, dayOfMonth));
    }

    public T setTime(int hours, int minutes) {
        return performAction(PickerActions.setTime(hours, minutes));
    }

    @Deprecated
    // Espresso 2.0 provides contrib.PickerActions
    public T updateDate(Date date) {
        return performAction(setDate(date));
    }

    @Deprecated
    // Espresso 2.0 provides contrib.PickerActions
    private ViewAction setDate(Date date) {
        return new PickDateAction(date);
    }

}
