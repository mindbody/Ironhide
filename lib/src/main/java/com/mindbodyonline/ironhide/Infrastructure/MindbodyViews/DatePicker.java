package com.mindbodyonline.ironhide.Infrastructure.MindbodyViews;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.PickerActions;
import android.view.View;

import com.mindbodyonline.ironhide.Infrastructure.Extensions.PickDateAction;

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
public class DatePicker<T> extends MindbodyView<T> {

    public DatePicker(Class<T> type, int resourceId) {
        this.type = type;
        id = resourceId;
    }

    public DatePicker(Class<T> type, Matcher<View> selector) {
        this.type = type;
        this.selector = selector;
    }

    public DatePicker(Class<T> type, int resourceId, int stringResourceId) {
        this.type = type;
        id = resourceId;
        stringId = stringResourceId;
    }

    public DatePicker(Class<T> type, int resourceId, int stringResourceId, String displayText) {
        this.type = type;
        id = resourceId;
        stringId = stringResourceId;
        text = displayText;
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
