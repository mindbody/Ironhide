package com.mindbodyonline.ironhide.Infrastructure.MindbodyViews;

import com.mindbodyonline.ironhide.Infrastructure.Extensions.EnterTextAction;
import com.google.android.apps.common.testing.ui.espresso.action.ViewActions;
import com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers;

/**
 * Extends MindbodyView
 * Simple element that allows text interaction
 * Implements methods such as typeText, enterText, checkHintText
 * Only use this element when dealing with an editable text field (e.g.: an EditText View)
 *
 * @param <T> The model the current element will return when interacted with
 */
public class TextField<T> extends MindbodyView<T> {

    public TextField(Class<T> type, int resourceId) {
        this.type = type;
        id = resourceId;
    }

    public TextField(Class<T> type, int resourceId, int stringResourceId) {
        this.type = type;
        id = resourceId;
        stringId = stringResourceId;
    }

    public TextField(Class<T> type, int resourceId, int stringResourceId, String displayText) {
        this.type = type;
        id = resourceId;
        stringId = stringResourceId;
        text = displayText;
    }

    public T typeText(String toType) {
        return performAction(ViewActions.typeText(toType));
    }

    public T clearText() {
        return performAction(ViewActions.clearText());
    }

    public T typeTextIntoFocusedView(String stringToBeTyped) {
        return performAction(ViewActions.typeTextIntoFocusedView(stringToBeTyped));
    }

    public T enterText(String toType) {
        return performAction(enterTextAction(toType));
    }

    private EnterTextAction enterTextAction(String toType) {
        return new EnterTextAction(toType);
    }

    public boolean textFieldWithText(String string) {
        try {
            checkMatches(ViewMatchers.withText(string));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
