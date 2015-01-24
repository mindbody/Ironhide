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

    @Override
    public DatePicker<T> changeRoot() {
        return (DatePicker<T>) super.changeRoot();
    }

    @Override
    public DatePicker<T> inRoot(Matcher<Root> rootMatcher) {
        return (DatePicker<T>) super.inRoot(rootMatcher);
    }

    @Override
    public DatePicker<T> inDialogRoot() {
        return (DatePicker<T>) super.inDialogRoot();
    }

    @Override
    public DatePicker<T> inPlatformPopup() {
        return (DatePicker<T>) super.inPlatformPopup();
    }

    @Override
    public DatePicker<T> inTouchableRoot() {
        return (DatePicker<T>) super.inTouchableRoot();
    }

    @Override
    public DatePicker<T> inDecorView(Matcher<View> decorViewMatcher) {
        return (DatePicker<T>) super.inDecorView(decorViewMatcher);
    }

    @Override
    public DatePicker<T> inFocusableRoot() {
        return (DatePicker<T>) super.inFocusableRoot();
    }
}
