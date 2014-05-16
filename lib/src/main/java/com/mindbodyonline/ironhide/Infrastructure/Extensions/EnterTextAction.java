package com.mindbodyonline.ironhide.Infrastructure.Extensions;

import android.view.KeyEvent;
import android.view.View;

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
        uiController.loopMainThreadUntilIdle();

        for (char letter : toType.toUpperCase().toCharArray()) {
            if (Character.isLetter(letter))
                view.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, letter - 36));
            else if (letter == ' ')
                view.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_SPACE));

        }
    }
}
