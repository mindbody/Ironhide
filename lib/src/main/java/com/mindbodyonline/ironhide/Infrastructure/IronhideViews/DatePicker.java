package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.support.test.espresso.Root;
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

    @Override
    public DatePicker<T> changeRoot() {
        super.changeRoot();
        return this;
    }

    @Override
    public DatePicker<T> inRoot(Matcher<Root> rootMatcher) {
        super.inRoot(rootMatcher);
        return this;
    }
}
