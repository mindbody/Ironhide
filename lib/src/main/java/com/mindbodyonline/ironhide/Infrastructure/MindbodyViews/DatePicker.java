package com.mindbodyonline.ironhide.Infrastructure.MindbodyViews;

import com.mindbodyonline.ironhide.Infrastructure.Extensions.PickDateAction;
import android.view.View;

import com.google.android.apps.common.testing.ui.espresso.ViewAction;
import org.hamcrest.Matcher;

import java.util.Date;

import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;

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

    public T updateDate(Date date) {
        return performAction(setDate(date));
    }

    private ViewAction setDate(Date date) {
        return new PickDateAction(date);
    }

}
