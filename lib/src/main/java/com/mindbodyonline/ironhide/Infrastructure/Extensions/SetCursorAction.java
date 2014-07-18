package com.mindbodyonline.ironhide.Infrastructure.Extensions;

import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.google.android.apps.common.testing.ui.espresso.UiController;
import com.google.android.apps.common.testing.ui.espresso.ViewAction;
import com.google.android.apps.common.testing.ui.espresso.action.GeneralClickAction;
import com.google.android.apps.common.testing.ui.espresso.action.GeneralLocation;
import com.google.android.apps.common.testing.ui.espresso.action.Press;
import com.google.android.apps.common.testing.ui.espresso.action.Tap;

import org.hamcrest.Matcher;

import static com.google.android.apps.common.testing.testrunner.util.Checks.checkNotNull;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.allOf;

/**
 * A Custom ViewAction that enters text by simulating Key events on the Android keyboard
 * Useful when interacting with an EditText View which uses an OnTextChangedListener
 */
public class SetCursorAction implements ViewAction {

    public SetCursorAction() {

    }

    @Override
    public Matcher<View> getConstraints() {
        return allOf(isDisplayed());
    }

    @Override
    public String getDescription() {
        return "Type this string: ";
    }

    @Override
    public void perform(UiController uiController, View view) {
        new GeneralClickAction(Tap.SINGLE, GeneralLocation.CENTER, Press.FINGER).perform(uiController, view);
        uiController.loopMainThreadUntilIdle();

        EditText editTextView = (EditText)view;

        editTextView.setSelection(editTextView.getText().length());
    }
}