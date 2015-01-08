package com.mindbodyonline.ironhide.Infrastructure.Extensions;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;
import android.widget.DatePicker;

import org.hamcrest.Matcher;

import java.util.Date;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.internal.util.Checks.checkNotNull;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Custom ViewAction which allows to interact with a DatePicker View and change its Date
 * May be supported by Espresso in the future
 */
@Deprecated
// Espresso 2.0 provides contrib.PickerActions
public final class PickDateAction implements ViewAction {

    private final Date date;

    public PickDateAction(Date date) {
        checkNotNull(date);
        this.date = date;
    }

    @Override
    public Matcher<View> getConstraints() {
        return allOf(isDisplayed());
    }

    @Override
    public String getDescription() {
        return "set date: " + date.toString();
    }

    @Override
    public void perform(UiController uiController, View view) {
        DatePicker picker = (DatePicker) view;
        picker.updateDate(date.getYear(), date.getMonth(), date.getDay());
    }
}
