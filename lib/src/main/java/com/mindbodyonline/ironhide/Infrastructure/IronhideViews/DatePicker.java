package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.support.test.espresso.Root;
import android.support.test.espresso.contrib.PickerActions;
import android.view.View;

import com.mindbodyonline.ironhide.PageObjects.PageObject;

import org.hamcrest.Matcher;

/**
 * Simple element that allows to interact with a {@link android.widget.DatePicker} or {@link android.widget.TimePicker}
 *
 * @param <T> The model the current element will return when interacted with
 */
public class DatePicker<T extends PageObject> extends BaseView<T> {

    /** @see BaseView#BaseView(Class, org.hamcrest.Matcher) */
    public DatePicker(Class<T> type, Matcher<View> viewMatcher) {
        super(type, viewMatcher);
    }

    /** @see BaseView#BaseView(int) */
    public DatePicker(int resourceId) {
        super(resourceId);
    }

    /** @see BaseView#BaseView(org.hamcrest.Matcher) */
    public DatePicker(Matcher<View> selector) {
        super(selector);
    }

    /** @see BaseView#BaseView(int, int) */
    public DatePicker(int IGNORED, int stringResourceId) {
        super(IGNORED, stringResourceId);
    }

    /** @see BaseView#BaseView(String) */
    public DatePicker(String displayText) {
        super(displayText);
    }


    /** {@inheritDoc} */
    @Override
    protected <E extends PageObject> DatePicker<E> goesTo(Class<E> type) {
        return new DatePicker<>(type, getSelector());
    }

    /**
     * @see android.support.test.espresso.contrib.PickerActions#setDate(int, int, int)
     * @return The model reached by interacting with this element.
     */
    public T setDate(int year, int monthOfYear, int dayOfMonth) {
        return performAction(PickerActions.setDate(year, monthOfYear, dayOfMonth));
    }

    /**
     * @see android.support.test.espresso.contrib.PickerActions#setTime(int, int)
     * @return The model reached by interacting with this element.
     */
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
}
