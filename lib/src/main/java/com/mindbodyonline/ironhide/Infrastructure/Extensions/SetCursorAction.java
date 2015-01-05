package com.mindbodyonline.ironhide.Infrastructure.Extensions;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.GeneralClickAction;
import android.support.test.espresso.action.GeneralLocation;
import android.support.test.espresso.action.Press;
import android.support.test.espresso.action.Tap;
import android.view.View;
import android.widget.EditText;

import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
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