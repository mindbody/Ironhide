package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.support.test.espresso.Root;
import android.support.test.espresso.contrib.PickerActions;
import android.view.View;

import com.mindbodyonline.ironhide.PageObjects.PageObject;

import org.hamcrest.Matcher;

/**
 * Extends BaseView
 * Simple element that allows to interact with a DatePicker View
 * Implements updateDate method, which handles the PickDateAction ViewAction
 * Only use this element when dealing with an ImageView View
 *
 * @param <T> The model the current element will return when interacted with
 */
public class DatePicker<T extends PageObject> extends BaseView<T> {

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

    public DatePicker(int resourceId, int stringResourceId, String displayText) {
        super(displayText);
    }


    public T setDate(int year, int monthOfYear, int dayOfMonth) {
        return performAction(PickerActions.setDate(year, monthOfYear, dayOfMonth));
    }

    public T setTime(int hours, int minutes) {
        return performAction(PickerActions.setTime(hours, minutes));
    }

    /**
     * Root Matchers return LayoutView
     */

    /** {@inheritDoc} */
    @Override
    public DatePicker<T> changeRoot() {
        return (DatePicker<T>) super.changeRoot();
    }

    /** {@inheritDoc} */
    @Override
    public DatePicker<T> inRoot(Matcher<Root> rootMatcher) {
        return (DatePicker<T>) super.inRoot(rootMatcher);
    }

    /** {@inheritDoc} */
    @Override
    public DatePicker<T> inDialogRoot() {
        return (DatePicker<T>) super.inDialogRoot();
    }

    /** {@inheritDoc} */
    @Override
    public DatePicker<T> inPlatformPopup() {
        return (DatePicker<T>) super.inPlatformPopup();
    }

    /** {@inheritDoc} */
    @Override
    public DatePicker<T> inTouchableRoot() {
        return (DatePicker<T>) super.inTouchableRoot();
    }

    /** {@inheritDoc} */
    @Override
    public DatePicker<T> inDecorView(Matcher<View> decorViewMatcher) {
        return (DatePicker<T>) super.inDecorView(decorViewMatcher);
    }

    /** {@inheritDoc} */
    @Override
    public DatePicker<T> inFocusableRoot() {
        return (DatePicker<T>) super.inFocusableRoot();
    }

    @Override
    protected <E extends PageObject> DatePicker<E> goesTo(Class<E> type) {
        return new DatePicker<E>(type, getSelector());
    }
}
