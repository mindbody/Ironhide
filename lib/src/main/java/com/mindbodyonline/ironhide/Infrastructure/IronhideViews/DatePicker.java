package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.support.test.espresso.contrib.PickerActions;
import android.view.View;

import org.hamcrest.Matcher;

/**
 * Extends BaseView
 * Simple element that allows to interact with a DatePicker View
 * Implements updateDate method, which handles the PickDateAction ViewAction
 * Only use this element when dealing with an ImageView View
 *
 * @param <T> The model the current element will return when interacted with
 */
public class DatePicker<T> extends BaseView<T> {

    public DatePicker(Class<T> type, int resourceId) {
        super(type, resourceId);
    }

    public DatePicker(Class<T> type, Matcher<View> selector) {
        super(type, selector);
    }

    public DatePicker(Class<T> type, int IGNORED, int stringResourceId) {
        super(type, IGNORED, stringResourceId);
    }

    public DatePicker(Class<T> type, int resourceId, int stringResourceId, String displayText) {
        super(type, displayText);
    }


    public T setDate(int year, int monthOfYear, int dayOfMonth) {
        return performAction(PickerActions.setDate(year, monthOfYear, dayOfMonth));
    }

    public T setTime(int hours, int minutes) {
        return performAction(PickerActions.setTime(hours, minutes));
    }
}
