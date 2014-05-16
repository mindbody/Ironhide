package com.mindbodyonline.ironhide.Infrastructure.Extensions;

import android.view.View;
import android.widget.DatePicker;

import com.google.android.apps.common.testing.ui.espresso.UiController;
import com.google.android.apps.common.testing.ui.espresso.ViewAction;
import org.hamcrest.Matcher;

import java.util.Date;

import static com.google.android.apps.common.testing.testrunner.util.Checks.checkNotNull;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Custom ViewAction which allows to interact with a DatePicker View and change its Date
 * May be supported by Espresso in the future
 */
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
