package com.mindbodyonline.ironhide.Infrastructure.Extensions;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.GeneralClickAction;
import android.support.test.espresso.action.GeneralLocation;
import android.support.test.espresso.action.Press;
import android.support.test.espresso.action.Tap;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.internal.util.Checks.checkNotNull;
import static org.hamcrest.Matchers.allOf;

/**
 * A Custom ViewAction that enters text by simulating Key events on the Android keyboard
 * Useful when interacting with an EditText View which uses an OnTextChangedListener
 */
public class EnterTextAction implements ViewAction {

    private String toType;

    public EnterTextAction(String toType) {
        checkNotNull(toType);
        this.toType = toType;
    }

    @Override
    public Matcher<View> getConstraints() {
        return allOf(isDisplayed());
    }

    @Override
    public String getDescription() {
        return "Type this string: " + toType;
    }

    @Override
    public void perform(UiController uiController, View view) {
        new GeneralClickAction(Tap.SINGLE, GeneralLocation.CENTER, Press.FINGER).perform(uiController, view);
        EditText input = (EditText) view;
        input.setSelection(input.getText().length());
        uiController.loopMainThreadUntilIdle();

        for (char letter : toType.toUpperCase().toCharArray()) {
            if(letter == '*')
                view.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
            else if (Character.isLetter(letter))
                view.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, letter - 36));
            else if(Character.isDigit(letter))
                view.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, letter + 96));
            else if (letter == ' ')
                view.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_SPACE));

        }
    }
}
